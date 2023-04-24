package com.kopchak.worldoftoys.dto.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private LocalDateTime orderDateTime;
    private BigDecimal totalPrice;
    private String stripePublicKey;
}
