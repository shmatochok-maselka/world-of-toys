package com.kopchak.worldoftoys.dto.order;

import com.kopchak.worldoftoys.model.order.ShippingMethod;
import com.kopchak.worldoftoys.model.order.ShippingOption;
import com.kopchak.worldoftoys.model.order.ShippingType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Shipping option details")
public class ShippingOptionDto {
    @Schema(example = "EXPRESS", requiredMode = Schema.RequiredMode.REQUIRED)
    @NonNull
    private ShippingType shippingType;

    @Schema(example = "COURIER", requiredMode = Schema.RequiredMode.REQUIRED)
    @NonNull
    private ShippingMethod shippingMethod;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NonNull
    private BigDecimal price;

    public ShippingOptionDto(ShippingOption shippingOption) {
        this.shippingType = shippingOption.getType();
        this.shippingMethod = shippingOption.getMethod();
        this.price = shippingOption.getPrice();
    }
}
