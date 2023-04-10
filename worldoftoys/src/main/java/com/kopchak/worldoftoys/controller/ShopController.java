package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ProductDto;
import com.kopchak.worldoftoys.repository.ProductRepository;
import com.kopchak.worldoftoys.service.ProductService;
import com.kopchak.worldoftoys.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

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
}
