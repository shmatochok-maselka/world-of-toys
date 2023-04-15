package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.ProductCartDto;

import java.security.Principal;

public interface CartService {
    void addProductToCart(ProductCartDto productCartDto, Principal principal);
}
