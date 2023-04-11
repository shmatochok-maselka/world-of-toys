package com.kopchak.worldoftoys.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
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
