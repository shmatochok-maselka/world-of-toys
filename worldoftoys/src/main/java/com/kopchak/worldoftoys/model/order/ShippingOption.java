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
public class ShippingOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ShippingType type;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ShippingMethod method;

    @NotBlank(message = "Price is mandatory")
    private BigDecimal price;

    @OneToMany
    @JoinColumn(name = "shipping_id")
    private Set<Order> orders;
}
