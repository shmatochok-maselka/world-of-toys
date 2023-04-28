package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.product.ProductShopDto;
import com.kopchak.worldoftoys.dto.token.TokenAuthDto;
import com.kopchak.worldoftoys.dto.user.UserAuthDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.model.user.Role;
import com.kopchak.worldoftoys.service.AuthenticationService;
import com.kopchak.worldoftoys.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.Set;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Controller for")
public class AdminController {
    private final ProductService productService;
    private final AuthenticationService authenticationService;


    @Operation(summary = "Admin login to the account")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login succeed",
                    content = @Content(schema = @Schema(implementation = TokenAuthDto.class))),
            @ApiResponse(
                    responseCode = "401",
                    description = "Bad user credentials!",
                    content = @Content(schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @PostMapping("/authenticate")
    public ResponseEntity<TokenAuthDto> authenticate(@Valid @RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(userAuthDto, Role.ADMIN));
    }
}
