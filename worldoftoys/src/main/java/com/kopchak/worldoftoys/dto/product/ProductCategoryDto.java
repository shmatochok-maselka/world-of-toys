package com.kopchak.worldoftoys.dto.product;

import com.kopchak.worldoftoys.model.product.category.ProductCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private String name;

    public ProductCategoryDto(ProductCategory productCategory) {
        this.name = productCategory.getName();
    }
}
