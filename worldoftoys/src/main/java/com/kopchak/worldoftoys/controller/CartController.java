package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.ProductCartDto;
import com.kopchak.worldoftoys.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/add-product")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductCartDto productCartDto, Principal principal) {
        cartService.addProductToCart(productCartDto, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
