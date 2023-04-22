package com.kopchak.worldoftoys.dto;

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
    private ShippingType shippingType;

    private ShippingMethod shippingMethod;

    private BigDecimal price;

    public ShippingOptionDto(ShippingOption shippingOption) {
        this.shippingType = shippingOption.getShippingType();
        this.shippingMethod = shippingOption.getShippingMethod();
        this.price = shippingOption.getPrice();
    }
}
