package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.cart.CartItemRequestDto;
import com.kopchak.worldoftoys.exception.ProductNotFoundException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "Controller for ")
public class UserController {
    private final UserService userService;

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
    public ResponseEntity<?> addProductToCart(
            @Valid @Schema(
                    description = "The data of product to be added to the cart",
                    implementation = CartItemRequestDto.class)
            @RequestBody CartItemRequestDto cartItemRequestDTO,
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                    example = "Bearer access_token") Principal principal) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
