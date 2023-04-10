package com.kopchak.worldoftoys.model.productcategory;

import com.kopchak.worldoftoys.model.Product;
import com.kopchak.worldoftoys.model.productcategory.ProductCategories;
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
public class TypeCategory extends ProductCategories {
    @OneToMany(mappedBy = "typeCategory")
    private Set<Product> products;
}
