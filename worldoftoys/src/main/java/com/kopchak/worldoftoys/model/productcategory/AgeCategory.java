package com.kopchak.worldoftoys.model.productcategory;

import com.kopchak.worldoftoys.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AgeCategory extends ProductCategories {
    @ManyToMany(mappedBy = "ageCategory")
    private Set<Product> products;
}
