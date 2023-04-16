package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.CartItemResponseDTO;
import com.kopchak.worldoftoys.dto.CartItemRequestDTO;

import java.security.Principal;
import java.util.Set;

public interface CartService {
    void addProductToCart(CartItemRequestDTO cartItemRequestDTO, Principal principal);
    Set<CartItemResponseDTO> getCartProducts(Principal principal);
    void updateCartItemQuantity(CartItemRequestDTO cartItemRequestDTO, Principal principal);
    void removeProductFromCart(CartItemRequestDTO cartItemRequestDTO, Principal principal);
}
