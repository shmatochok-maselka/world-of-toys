package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.CartItemRequestDto;
import com.kopchak.worldoftoys.dto.CartItemResponseDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.CartItemId;
import com.kopchak.worldoftoys.model.CartItem;
import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.User;
import com.kopchak.worldoftoys.repository.CartItemsRepository;
import com.kopchak.worldoftoys.repository.ProductRepository;
import com.kopchak.worldoftoys.repository.UserRepository;
import com.kopchak.worldoftoys.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addProductToCart(CartItemRequestDto cartItemRequestDto, Principal principal) {
        CartItemId cartItemId = getCartItemId(cartItemRequestDto, principal);
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItemsRepository.save(cartItem);
    }

    @Override
    public Set<CartItemResponseDto> getCartProducts(Principal principal) {
        User user = getUserByPrincipal(principal);
        return cartItemsRepository.findAllProductsByUserId(user.getId());
    }

    @Override
    public void updateCartItemQuantity(CartItemRequestDto cartItemRequestDto, Principal principal) {
        CartItemId cartItemId = getCartItemId(cartItemRequestDto, principal);
        CartItem cartItem = cartItemsRepository.findById(cartItemId).orElseThrow(() ->
                new ProductNotFoundException(HttpStatus.NOT_FOUND, "There is no such product in the cart"));
        cartItemsRepository.updateCartItemQuantity(cartItem.getId(), cartItemRequestDto.getQuantity());
    }

    @Override
    public void removeProductFromCart(CartItemRequestDto cartItemRequestDto, Principal principal) {
        cartItemsRepository.deleteCartItemsById(getCartItemId(cartItemRequestDto, principal));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
    }

    private CartItemId getCartItemId(CartItemRequestDto cartItemRequestDto, Principal principal) {
        User user = getUserByPrincipal(principal);
        Product product = productRepository.findBySlug(cartItemRequestDto.getSlug());
        if (cartItemRequestDto.getSlug() == null || product == null) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        return new CartItemId(user, product);
    }
}
