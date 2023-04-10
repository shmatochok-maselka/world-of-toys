package com.kopchak.worldoftoys.model.productcategory;

import com.kopchak.worldoftoys.model.Product;
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
public class GenderCategory extends ProductCategories {
    @OneToMany(mappedBy = "genderCategory")
    private Set<Product> products;
}
