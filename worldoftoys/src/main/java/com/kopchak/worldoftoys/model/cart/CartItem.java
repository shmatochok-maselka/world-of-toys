package com.kopchak.worldoftoys.model.cart;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @EmbeddedId
    @NonNull
    private CartItemId id;

    @Builder.Default
    @NonNull
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private BigInteger quantity = BigInteger.ONE;
}
