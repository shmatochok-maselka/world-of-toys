package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.ProductCartDto;
import com.kopchak.worldoftoys.dto.ProductShopDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @Operation(summary = "Add product to card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content),
            @ApiResponse(responseCode = "401", description = "Access denied because user is unauthorized"),
            @ApiResponse(responseCode = "404", description = "User or product not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(oneOf = {
                           UserNotFoundException.class, ProductNotFoundException.class })))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/add-product")
    public ResponseEntity<?> addProductToCart(
             @Valid @Schema(description = "The slug of the product to be added to the cart",
                     implementation = ProductCartDto.class) @RequestBody ProductCartDto productCartDto,
             @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                     example = "Bearer access_token") Principal principal) {
        cartService.addProductToCart(productCartDto, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<ProductShopDto>> getCartProducts(Principal principal) {
        return new ResponseEntity<>(cartService.getCartProducts(principal), HttpStatus.OK);
    }
}
