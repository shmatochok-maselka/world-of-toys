package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ProductShopDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
@Tag(name = "shop-controller", description = "Controller for return all products in the shop page, return a specific " +
        "product using its slug, return all available product categories for filtering in the frontend")
public class ShopController {
    private final ProductService productService;

    @Operation(summary = "Return all products in the shop page")
    @ApiResponse(
            responseCode = "200",
            description = "Products have been successfully returned",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ProductShopDto.class))
                    )
            })
    @GetMapping
    public ResponseEntity<Set<ProductShopDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Return all available product categories for filtering in the frontend")
    @ApiResponse(
            responseCode = "200",
            description = "Product categories have been successfully returned",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AllProductCategoriesDto.class)
                    )
            })
    @GetMapping("/categories")
    public ResponseEntity<AllProductCategoriesDto> getAllProductCategories() {
        return new ResponseEntity<>(productService.getAllCategories(), HttpStatus.OK);
    }

    @Operation(summary = "Return a specific product using its slug")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product has been successfully returned",
                    content = @Content(schema = @Schema(implementation = ProductShopDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Product does not exist!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductNotFoundException.class)))
    })
    @GetMapping("/{productSlug}")
    public ResponseEntity<ProductShopDto> getProductBySlug(@PathVariable String productSlug) {
        return new ResponseEntity<>(productService.getProductBySlug(productSlug), HttpStatus.OK);
    }
}
