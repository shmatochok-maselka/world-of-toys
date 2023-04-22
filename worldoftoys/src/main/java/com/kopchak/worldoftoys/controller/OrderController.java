package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.AllProductCategoriesDto;
import com.kopchak.worldoftoys.dto.ShippingOptionDto;
import com.kopchak.worldoftoys.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(value = {"http://localhost:4200", "http://localhost:8080"})
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Tag(name = "order-controller", description = "")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Return all shipping options for the order")
    @ApiResponse(
            responseCode = "200",
            description = "Shipping options have been successfully returned",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShippingOptionDto.class)
                    )
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/shipping-options")
    public ResponseEntity<Set<ShippingOptionDto>> getAllShippingOptions() {
        return new ResponseEntity<>(orderService.getAllShippingOptions(), HttpStatus.OK);
    }
}
