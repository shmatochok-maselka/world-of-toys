package com.kopchak.worldoftoys.model.order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "shipping_id")
    private Set<Order> orders;
}
