package com.kopchak.worldoftoys.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItems {
    @EmbeddedId
    @NonNull
    private CartItemId id;

    @NonNull
    private Integer quantity = 1;
}
