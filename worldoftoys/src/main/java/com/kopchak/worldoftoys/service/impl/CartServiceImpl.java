package com.kopchak.worldoftoys.service.impl;

import com.kopchak.worldoftoys.dto.CartItemResponseDTO;
import com.kopchak.worldoftoys.dto.CartItemRequestDTO;
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
    public void addProductToCart(CartItemRequestDTO cartItemRequestDTO, Principal principal) {
        CartItemId cartItemId = getCartItemId(cartItemRequestDTO, principal);
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItemsRepository.save(cartItem);
    }

    @Override
    public Set<CartItemResponseDTO> getCartProducts(Principal principal) {
        User user = getUserByPrincipal(principal);
        return cartItemsRepository.findAllProductsByUserId(user.getId());
    }

    @Override
    public void updateCartItemQuantity(CartItemRequestDTO cartItemRequestDTO, Principal principal) {
        CartItemId cartItemId = getCartItemId(cartItemRequestDTO, principal);
        CartItem cartItem = cartItemsRepository.findById(cartItemId).orElseThrow(() ->
                new ProductNotFoundException(HttpStatus.NOT_FOUND, "There is no such product in the cart"));
        cartItemsRepository.updateCartItemQuantity(cartItem.getId(), cartItemRequestDTO.getQuantity());
    }

    @Override
    public void removeProductFromCart(CartItemRequestDTO cartItemRequestDTO, Principal principal) {
        cartItemsRepository.deleteCartItemsById(getCartItemId(cartItemRequestDTO, principal));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException(HttpStatus.NOT_FOUND, "User does not exist!"));
    }

    private CartItemId getCartItemId(CartItemRequestDTO cartItemRequestDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Product product = productRepository.findBySlug(cartItemRequestDTO.getSlug());
        if (cartItemRequestDTO.getSlug() == null || product == null) {
            throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product does not exist!");
        }
        return new CartItemId(user, product);
    }
}
