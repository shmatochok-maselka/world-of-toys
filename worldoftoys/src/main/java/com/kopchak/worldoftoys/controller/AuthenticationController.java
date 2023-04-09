package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.*;
import com.kopchak.worldoftoys.service.AuthenticationService;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        authenticationService.register(userRegisterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/resend-verification-email")
    public ResponseEntity<?> resendVerificationEmail(@Valid @RequestBody UsernameDto username) {
        authenticationService.resendVerificationEmail(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody UsernameDto username) {
        authenticationService.resetPassword(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(confirmationTokenService.confirmToken(token));
    }

    @PostMapping(path = "/forgot-password")
    public ResponseEntity<String> changePassword(@RequestParam("token") String token,
                                                 @Valid @RequestBody PasswordResetDto newPassword) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(confirmationTokenService.confirmResetToken(token, newPassword));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenAuthDto> authenticate(@Valid @RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(userAuthDto));
    }
}
