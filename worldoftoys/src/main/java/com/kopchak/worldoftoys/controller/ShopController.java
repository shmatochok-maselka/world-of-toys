package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ProductDto;
import com.kopchak.worldoftoys.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Set<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<AllProductCategoriesDto> getAllProductCategories() {
        return new ResponseEntity<>(productService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{productSlug}")
    public ResponseEntity<ProductDto> getProductBySlug(@PathVariable String productSlug) {
        return new ResponseEntity<>(productService.getProductBySlug(productSlug), HttpStatus.OK);
    }
}
