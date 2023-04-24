package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.CartItemResponseDto;
import com.kopchak.worldoftoys.dto.order.*;
import com.kopchak.worldoftoys.exception.PaymentFailedException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.model.order.*;
import com.kopchak.worldoftoys.model.payment.Payment;
import com.kopchak.worldoftoys.model.payment.PaymentCurrency;
import com.kopchak.worldoftoys.repository.CartItemsRepository;
import com.kopchak.worldoftoys.repository.PaymentRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.repository.order.*;
import com.kopchak.worldoftoys.service.OrderPaymentService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderPaymentServiceImpl implements OrderPaymentService {
    @Value("${stripe.public.key}")
    private String stripePublicKey;
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    private final ShippingRepository shippingRepository;
    private final UserRepository userRepository;
    private final RecipientRepository recipientRepository;
    private final AddressRepository addressRepository;
    private final CartItemsRepository cartItemsRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Set<ShippingOptionDto> getAllShippingOptions() {
        return shippingRepository.findAll()
                .stream()
                .map(ShippingOptionDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public OrderDetailsDto makeOrder(OrderCreationDto orderCreationDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        Recipient recipient = recipientRepository.save(orderCreationDto.getRecipientDto().toRecipient());
        Address address = addressRepository.save(orderCreationDto.getAddressDto().toAddress());
        var shippingOptionDto = orderCreationDto.getShippingOptionDto();
        ShippingOption shippingOption = shippingRepository.findByTypeAndMethod(
                shippingOptionDto.getShippingType(), shippingOptionDto.getShippingMethod());
        BigDecimal totalPrice = calcTotalOrderPrice(user, shippingOption);
        Order order = createOrder(user, recipient, shippingOption, address, totalPrice);
        transferCartItemsToOrder(user, order);
        return new OrderDetailsDto(order.getDateTime(), order.getTotalPrice(), stripePublicKey);
    }

    @Override
    public void makeShippingPayment(PaymentCreationDto paymentCreationDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        Order order = orderRepository.searchByDateTimeAndUser(paymentCreationDto.getOrderDateTime(), user);
        if (order.getPayment() != null) {
            throw new PaymentFailedException(HttpStatus.BAD_REQUEST, "The order has already been paid");
        }
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentCreationDto.getTotalPrice());
        chargeParams.put("currency", PaymentCurrency.UAH);
        chargeParams.put("description", "Payment for toys in the online store \"World of toys\"");
        chargeParams.put("source", paymentCreationDto.getStripeToken());
        try {
            Charge charge = Charge.create(chargeParams);
            Payment payment = Payment
                    .builder()
                    .id(charge.getId())
                    .amount(paymentCreationDto.getTotalPrice())
                    .currency(PaymentCurrency.UAH)
                    .description(charge.getDescription())
                    .source(charge.getSource().getId())
                    .build();
            payment = paymentRepository.save(payment);
            order.setStatus(OrderStatus.PROCESSING);
            order.setPayment(payment);
            orderRepository.save(order);
        } catch (StripeException e) {
            throw new PaymentFailedException(HttpStatus.PAYMENT_REQUIRED, "Payment failed: " + e.getMessage());
        }
    }

    @Override
    public Set<UserOrderDto> getUserOrders(Principal principal) {
        User user = getUserByPrincipal(principal);
        Set<Order> orders = orderRepository.searchAllByUser(user);
        return orders
                .stream()
                .map(order -> UserOrderDto
                        .builder()
                        .dateTime(order.getDateTime())
                        .status(order.getStatus())
                        .totalPrice(order.getTotalPrice())
                        .products(convertOrderItemToCartItemDtoSet(
                                orderItemRepository.searchAllByOrderId(order.getId())))
                        .build())
                .collect(Collectors.toSet());
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
                .dateTime(LocalDateTime.now())
                .status(OrderStatus.NEW)
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

    private User getUserByPrincipal(Principal principal) {
        String email = principal.getName();
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    private Set<CartItemResponseDto> convertOrderItemToCartItemDtoSet(Set<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> CartItemResponseDto
                        .builder()
                        .name(orderItem.getId().getProduct().getName())
                        .slug(orderItem.getId().getProduct().getSlug())
                        .image(orderItem.getId().getProduct().getImage())
                        .price(orderItem.getId().getProduct().getPrice())
                        .quantity(orderItem.getQuantity())
                        .build())
                .collect(Collectors.toSet());
    }
}
