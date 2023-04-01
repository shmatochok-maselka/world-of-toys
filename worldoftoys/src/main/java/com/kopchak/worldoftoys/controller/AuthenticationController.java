package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.TokenAuthDto;
import com.kopchak.worldoftoys.dto.UserAuthDto;
import com.kopchak.worldoftoys.dto.UserRegisterDto;
import com.kopchak.worldoftoys.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authenticationService.register(userRegisterDto));
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationService.confirmToken(token));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenAuthDto> authenticate(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authenticationService.authenticate(userAuthDto));
    }
}
