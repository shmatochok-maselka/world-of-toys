package com.kopchak.worldoftoys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllProductCategoriesDto {

    private Double minProductPrice;

    private Double maxProductPrice;

    private Set<ProductCategoryDto> ageCategories;

    private Set<ProductCategoryDto> genderCategories;

    private Set<ProductCategoryDto> originCategories;

    private Set<TypeCategoryDto> typeCategories;
}
