package com.kopchak.worldoftoys.model.cart;

import com.kopchak.worldoftoys.model.product.Product;
import com.kopchak.worldoftoys.model.user.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CartItemId implements Serializable {
    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "product_id")
    private Product product;

}
