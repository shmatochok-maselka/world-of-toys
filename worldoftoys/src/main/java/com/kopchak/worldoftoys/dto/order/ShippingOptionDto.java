package com.kopchak.worldoftoys.dto.order;

import com.kopchak.worldoftoys.model.order.ShippingMethod;
import com.kopchak.worldoftoys.model.order.ShippingOption;
import com.kopchak.worldoftoys.model.order.ShippingType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingOptionDto {
    @NonNull
    private ShippingType shippingType;

    @NonNull
    private ShippingMethod shippingMethod;

    @NonNull
    private BigDecimal price;

    public ShippingOptionDto(ShippingOption shippingOption) {
        this.shippingType = shippingOption.getType();
        this.shippingMethod = shippingOption.getMethod();
        this.price = shippingOption.getPrice();
    }
}
