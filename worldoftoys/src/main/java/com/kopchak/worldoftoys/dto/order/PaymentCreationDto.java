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
public class PaymentCreationDto {
    @Schema(example = "2023-04-25T12:35:25.008152", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime orderDateTime;

    @Schema(example = "2999.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal totalPrice;

    @Schema(example = "tok_stripetoken", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stripeToken;
}
