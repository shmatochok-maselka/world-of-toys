package com.kopchak.worldoftoys.model;

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
public class TypeCategory extends ProductCategories{
    @ManyToMany
    private Set<BrandCategory> brandCategories;

    @ManyToMany(mappedBy = "typeCategories")
    private Set<Product> products;
}
