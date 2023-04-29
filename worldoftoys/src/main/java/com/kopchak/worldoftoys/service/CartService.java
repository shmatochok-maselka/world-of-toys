package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.cart.CartItemRequestDto;
import com.kopchak.worldoftoys.dto.cart.CartItemResponseDto;

import java.security.Principal;
import java.util.Set;

public interface CartService {
    String addProductToCart(CartItemRequestDto cartItemRequestDto, Principal principal);
    Set<CartItemResponseDto> getCartProducts(Principal principal);
    String updateCartItemQuantity(CartItemRequestDto cartItemRequestDto, Principal principal);
    void removeProductFromCart(CartItemRequestDto cartItemRequestDto, Principal principal);
}
