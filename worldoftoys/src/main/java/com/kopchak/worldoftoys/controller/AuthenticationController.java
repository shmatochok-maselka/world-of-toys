package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.service.AuthenticationService;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<TokenAuthDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.register(userRegisterDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenAuthDto> authenticate(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.authenticate(userAuthDto));
    }
}
