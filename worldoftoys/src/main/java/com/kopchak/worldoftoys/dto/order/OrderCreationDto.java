package com.kopchak.worldoftoys.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private RecipientDto recipientDto;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private AddressDto addressDto;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private ShippingOptionDto shippingOptionDto;
}
