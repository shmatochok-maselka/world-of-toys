package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.cart.CartItemRequestDto;
import com.kopchak.worldoftoys.dto.cart.CartItemResponseDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "cart-controller", description = "Controller for managing products in the user's cart")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "Add product to card")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product has been successfully added to the cart",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User or product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {
                                    UserNotFoundException.class, ProductNotFoundException.class
                            })))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/add-product")
    public ResponseEntity<String> addProductToCart(
            @Valid @Schema(
                    description = "The data of product to be added to the cart",
                    implementation = CartItemRequestDto.class)
            @RequestBody CartItemRequestDto cartItemRequestDTO,
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                    example = "Bearer access_token") Principal principal) {
        return new ResponseEntity<>(cartService.addProductToCart(cartItemRequestDTO, principal), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all products from the cart",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All cart products have been successfully returned",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = CartItemResponseDto.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserNotFoundException.class)))
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<Set<CartItemResponseDto>> getCartProducts(
            @Parameter(in = ParameterIn.HEADER,
                    name = "Authorization",
                    description = "JWT auth token",
                    required = true,
                    example = "Bearer access_token") Principal principal) {
        return new ResponseEntity<>(cartService.getCartProducts(principal), HttpStatus.OK);
    }

    @Operation(summary = "Update product quantity in the cart")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product quantity has been successfully updated in the cart",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User or product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {
                                    UserNotFoundException.class, ProductNotFoundException.class
                            })))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping()
    public ResponseEntity<?> updateCartItemQuantity(
            @Valid @Schema(
                    description = "Update product data",
                    implementation = CartItemRequestDto.class)
            @RequestBody CartItemRequestDto cartItemRequestDto,
            @Parameter(in = ParameterIn.HEADER,
                    name = "Authorization",
                    description = "JWT auth token",
                    required = true,
                    example = "Bearer access_token") Principal principal) {
        return new ResponseEntity<>(cartService.updateCartItemQuantity(cartItemRequestDto, principal), HttpStatus.OK);
    }

    @Operation(summary = "Delete product from the cart")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Product has been successfully deleted from the cart",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User or product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(oneOf = {
                                    UserNotFoundException.class, ProductNotFoundException.class
                            })))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping()
    public ResponseEntity<?> removeProductFromCart(
            @Valid @Schema(
                    description = "Data about the product to be removed",
                    implementation = CartItemRequestDto.class)
            @RequestBody CartItemRequestDto cartItemRequestDto,
            @Parameter(in = ParameterIn.HEADER,
                    name = "Authorization",
                    description = "JWT auth token",
                    required = true,
                    example = "Bearer access_token") Principal principal) {
        cartService.removeProductFromCart(cartItemRequestDto, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
