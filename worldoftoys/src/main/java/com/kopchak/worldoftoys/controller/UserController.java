package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.cart.CartItemRequestDto;
import com.kopchak.worldoftoys.dto.user.ChangePasswordDto;
import com.kopchak.worldoftoys.dto.user.UserUpdateDto;
import com.kopchak.worldoftoys.exception.IncorrectPasswordException;
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

    @Operation(summary = "Update user account information")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User data has been successfully changed",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/update")
    public ResponseEntity<?> updateUser(
            @Valid @Schema(
                    description = "The data of product to be added to the cart",
                    implementation = CartItemRequestDto.class)
            @RequestBody UserUpdateDto userUpdateDto,
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                    example = "Bearer access_token") Principal principal) {
        userService.updateUser(userUpdateDto, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update user account information")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password has been successfully changed",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "400",
                    description = "Password is incorrect",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = IncorrectPasswordException.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/change-password")
    public ResponseEntity<?> changePassword(
            @Valid @Schema(
                    implementation = ChangePasswordDto.class)
            @RequestBody ChangePasswordDto changePasswordDto,
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                    example = "Bearer access_token") Principal principal) {
        userService.changePassword(changePasswordDto, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
