package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.CartItemResponseDTO;
import com.kopchak.worldoftoys.dto.ProductCartDto;
import com.kopchak.worldoftoys.dto.ProductShopDto;

import java.security.Principal;
import java.util.Set;

public interface CartService {
    void addProductToCart(ProductCartDto productCartDto, Principal principal);
//    Set<ProductShopDto> getCartProducts(Principal principal);
    Set<CartItemResponseDTO> getCartProducts(Principal principal);
    void updateCartItemQuantity(ProductCartDto productCartDto, Principal principal);
}
