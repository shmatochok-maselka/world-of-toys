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
    private CartItemId id;

    private Integer quantity = 1;
}
