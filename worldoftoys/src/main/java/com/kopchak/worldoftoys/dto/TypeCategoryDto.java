package com.kopchak.worldoftoys.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeCategoryDto extends  ProductCategoryDto{
    Set<ProductCategoryDto> brandCategories;

    public TypeCategoryDto(String name, Set<ProductCategoryDto> brandCategories) {
        super(name);
        this.brandCategories = brandCategories;
    }
}
