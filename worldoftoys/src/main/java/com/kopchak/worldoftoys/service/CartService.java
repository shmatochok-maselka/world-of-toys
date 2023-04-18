package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.CartItemRequestDto;
import com.kopchak.worldoftoys.dto.CartItemResponseDto;

import java.security.Principal;
import java.util.Set;

public interface CartService {
    void addProductToCart(CartItemRequestDto cartItemRequestDto, Principal principal);
    Set<CartItemResponseDto> getCartProducts(Principal principal);
    void updateCartItemQuantity(CartItemRequestDto cartItemRequestDto, Principal principal);
    void removeProductFromCart(CartItemRequestDto cartItemRequestDto, Principal principal);
}
