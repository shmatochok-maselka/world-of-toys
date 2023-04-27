package com.kopchak.worldoftoys.model.product.category;

import com.kopchak.worldoftoys.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BrandCategory extends ProductCategory {
    @OneToMany(mappedBy = "brandCategory")
    private Set<Product> products;
}
