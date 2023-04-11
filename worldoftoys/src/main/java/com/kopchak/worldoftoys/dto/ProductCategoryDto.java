package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.productcategory.ProductCategories;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private String name;

    public ProductCategoryDto(ProductCategories productCategories) {
        this.name = productCategories.getName();
    }
}
