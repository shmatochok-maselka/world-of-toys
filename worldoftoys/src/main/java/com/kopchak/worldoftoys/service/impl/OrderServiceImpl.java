package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.order.OrderDto;
import com.kopchak.worldoftoys.dto.order.ShippingOptionDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.model.order.*;
import com.kopchak.worldoftoys.repository.CartItemsRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.repository.order.*;
import com.kopchak.worldoftoys.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShippingRepository shippingRepository;
    private final UserRepository userRepository;
    private final RecipientRepository recipientRepository;
    private final AddressRepository addressRepository;
    private final CartItemsRepository cartItemsRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<ShippingOptionDto> getAllShippingOptions() {
        return shippingRepository.findAll()
                .stream()
                .map(ShippingOptionDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void makeOrder(OrderDto orderDto, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found!"));
        Recipient recipient = recipientRepository.save(orderDto.getRecipientDto().toRecipient());
        Address address = addressRepository.save(orderDto.getAddressDto().toAddress());
        var shippingOptionDto = orderDto.getShippingOptionDto();
        ShippingOption shippingOption = shippingRepository.findByTypeAndMethod(
                shippingOptionDto.getShippingType(), shippingOptionDto.getShippingMethod());
        BigDecimal totalPrice = calcTotalOrderPrice(user, shippingOption);
        Order order = createOrder(user, recipient, shippingOption, address, totalPrice);
        transferCartItemsToOrder(user, order);
    }

    private BigDecimal calcTotalOrderPrice(User user, ShippingOption shippingOption) {
        var cartProducts = cartItemsRepository.findAllProductsByUserId(user.getId());
        BigDecimal totalPriceOfProducts = cartProducts
                .stream()
                .map(cartItem -> cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPriceOfProducts.add(shippingOption.getPrice());
    }

    private Order createOrder(User user, Recipient recipient,
                              ShippingOption shippingOption, Address address, BigDecimal totalPrice) {
        Order order = Order
                .builder()
                .orderDateTime(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .totalPrice(totalPrice)
                .user(user)
                .recipient(recipient)
                .shippingOption(shippingOption)
                .address(address)
                .build();
        return orderRepository.save(order);
    }

    @Transactional
    private void transferCartItemsToOrder(User user, Order order) {
        var cartItems = cartItemsRepository.findAllCartItemsByUserId(user.getId());
        Set<OrderItem> orderItems = cartItems
                .stream()
                .map(cartItem ->
                        OrderItem.
                                builder()
                                .id(new OrderItemId(order, cartItem.getId().getProduct()))
                                .quantity(cartItem.getQuantity())
                                .build()
                ).collect(Collectors.toSet());
        orderItemRepository.saveAll(orderItems);
        cartItemsRepository.deleteCartItemsByUserId(user.getId());
    }
}
