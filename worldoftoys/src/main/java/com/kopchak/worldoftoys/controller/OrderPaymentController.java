package com.kopchak.worldoftoys.controller;

import com.kopchak.worldoftoys.dto.order.*;
import com.kopchak.worldoftoys.exception.PaymentFailedException;
import com.kopchak.worldoftoys.exception.UserNotFoundException;
import com.kopchak.worldoftoys.service.OrderPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "order-controller", description = "Controller for")
public class OrderPaymentController {
    private final OrderPaymentService orderPaymentService;

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
        return new ResponseEntity<>(orderPaymentService.getAllShippingOptions(), HttpStatus.OK);
    }

    @Operation(summary = "Make order",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Order has been successfully created",
                            content = @Content(schema = @Schema(implementation = OrderDetailsDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserNotFoundException.class)))
            })
    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<OrderDetailsDto> makeOrder(@Valid @Schema(
            description = "The data of order",
            implementation = OrderCreationDto.class) @RequestBody OrderCreationDto orderCreationDto,
                                                     @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                                                             example = "Bearer access_token") Principal principal) {
        return new ResponseEntity<>(orderPaymentService.makeOrder(orderCreationDto, principal), HttpStatus.CREATED);
    }

    @Operation(summary = "Pay for the order",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Payment succeeded",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "402",
                            description = "Payment failed",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentFailedException.class)))
            })
    @PostMapping("/payment")
    public ResponseEntity<?> makeShippingPayment(@Valid @Schema(
            description = "The data for payment",
            implementation = PaymentCreationDto.class) @RequestBody PaymentCreationDto paymentCreationDto,
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
            example = "Bearer access_token") Principal principal) {
        orderPaymentService.makeShippingPayment(paymentCreationDto, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all user's orders",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Orders have been successfully returned",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserNotFoundException.class)))
            })
    @GetMapping("/user")
    public ResponseEntity<Set<UserOrderDto>> getUserOrders(
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "JWT auth token", required = true,
                   example = "Bearer access_token") Principal principal) {
        return new ResponseEntity<>(orderPaymentService.getUserOrders(principal), HttpStatus.OK);
    }
}
