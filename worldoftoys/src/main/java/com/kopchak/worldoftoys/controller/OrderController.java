package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.CartItemResponseDto;
import com.kopchak.worldoftoys.dto.order.OrderDto;
import com.kopchak.worldoftoys.dto.order.ShippingOptionDto;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Make order",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Order has been successfully created",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserNotFoundException.class)))
            })
    @PostMapping()
    public ResponseEntity<?> makeOrder(@Valid @Schema(
            description = "The data of order",
            implementation = OrderDto.class) @RequestBody OrderDto orderDto,
                                       @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                                               example = "Bearer access_token") Principal principal) {
        orderService.makeOrder(orderDto, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
