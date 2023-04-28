package com.kopchak.worldoftoys.dto.product;

import com.kopchak.worldoftoys.model.product.category.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    public ProductCategoryDto(ProductCategory productCategory) {
        this.name = productCategory.getName();
    }
}
