package com.kopchak.worldoftoys.dto.product;

import com.kopchak.worldoftoys.dto.product.ProductCategoryDto;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeCategoryDto extends ProductCategoryDto {
    Set<ProductCategoryDto> brandCategories;

    public TypeCategoryDto(String name, Set<ProductCategoryDto> brandCategories) {
        super(name);
        this.brandCategories = brandCategories;
    }
}
