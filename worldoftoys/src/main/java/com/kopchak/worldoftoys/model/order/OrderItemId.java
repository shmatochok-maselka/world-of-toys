package com.kopchak.worldoftoys.model.order;

import com.kopchak.worldoftoys.model.product.Product;
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
public class OrderItemId implements Serializable {
    @ManyToOne
    @NonNull
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "product_id")
    private Product product;
}
