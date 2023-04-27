package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.token.TokenAuthDto;
import com.kopchak.worldoftoys.dto.user.*;
import com.kopchak.worldoftoys.exception.AccountIsAlreadyActivatedException;
import com.kopchak.worldoftoys.exception.IncorrectPasswordException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.exception.UsernameAlreadyExistException;
import com.kopchak.worldoftoys.service.AuthenticationService;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "authentication-controller", description = "Controller for user registration, account confirmation, " +
        "reset password and login")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ConfirmationTokenService confirmationTokenService;

    @Operation(summary = "User registration")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User has been successfully registered",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409",
                    description = "Username already exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsernameAlreadyExistException.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        authenticationService.register(userRegisterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Resend verification email for account activation")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Verification email has been successfully sent",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserNotFoundException.class))),
            @ApiResponse(responseCode = "409",
                    description = "Account is already activated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountIsAlreadyActivatedException.class)))
    })
    @PostMapping("/resend-verification-email")
    public ResponseEntity<?> resendVerificationEmail(@Schema(
            description = "Username to activate the account",
            implementation = UsernameDto.class) @Valid @RequestBody UsernameDto username) {
        authenticationService.resendVerificationEmail(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Send an email with a link to reset user password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Reset password email has been successfully sent",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Schema(
            description = "Username to reset the password",
            implementation = UsernameDto.class)@Valid @RequestBody UsernameDto username) {
        authenticationService.resetPassword(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Account activation")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account activated!",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@Parameter(
            description = "User account activation token",
            required = true) @RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(confirmationTokenService.confirmToken(token));
    }

    @Operation(summary = "Reset password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account activated!",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found or new password matches old password!",
                    content = @Content(schema = @Schema(oneOf = {
                            UserNotFoundException.class, IncorrectPasswordException.class
                    })))
    })
    @PostMapping(path = "/forgot-password")
    public ResponseEntity<String> changePassword(@Parameter(
            description = "Token to change the user's password",
            required = true) @RequestParam("token") String token,
            @Valid @RequestBody ResetPasswordDto newPassword) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(confirmationTokenService.confirmResetToken(token, newPassword));
    }

    @Operation(summary = "User login to the account")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account activated!",
                    content = @Content(schema = @Schema(implementation = TokenAuthDto.class))),
            @ApiResponse(
                    responseCode = "401",
                    description = "Bad user credentials!",
                    content = @Content(schema = @Schema(implementation = UserNotFoundException.class))),
            @ApiResponse(
                    responseCode = "403",
                    description = "Account is not activated!",
                    content = @Content(schema = @Schema(implementation = UserNotFoundException.class))),
    })
    @PostMapping("/authenticate")
    public ResponseEntity<TokenAuthDto> authenticate(@Valid @RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(userAuthDto));
    }
}
