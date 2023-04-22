package com.kopchak.worldoftoys.model.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ShippingType shippingType;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ShippingMethod shippingMethod;

    @NotBlank(message = "Price is mandatory")
    private BigDecimal price;
}
