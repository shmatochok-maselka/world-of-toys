package com.kopchak.worldoftoys.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Order details for payment via Stripe")
public class OrderDetailsDto {
    @Schema(example = "2023-04-25T12:35:25.008152")
    private LocalDateTime orderDateTime;

    @Schema(example = "2999.00")
    private BigDecimal totalPrice;

    @Schema(example = "pk_test_publictestkey")
    private String stripePublicKey;
}
