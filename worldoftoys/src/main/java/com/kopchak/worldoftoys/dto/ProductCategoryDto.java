package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.productcategory.ProductCategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private String name;

    public ProductCategoryDto(ProductCategories productCategories) {
        this.name = productCategories.getName();
    }
}
