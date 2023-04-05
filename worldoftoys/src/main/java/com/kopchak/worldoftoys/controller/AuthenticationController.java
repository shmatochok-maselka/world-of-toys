package com.kopchak.worldoftoys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.service.AuthenticationService;
import com.kopchak.worldoftoys.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto) {
        authenticationService.register(userRegisterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/resend-verification-email")
    public ResponseEntity<?> resendVerificationEmail(@RequestBody JsonNode user) {
        String username = user.get("email").asText();
        authenticationService.resendVerificationEmail(username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(confirmationTokenService.confirmToken(token));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenAuthDto> authenticate(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(userAuthDto));
    }
}
