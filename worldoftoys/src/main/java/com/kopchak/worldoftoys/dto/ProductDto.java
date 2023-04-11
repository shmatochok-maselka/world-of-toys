package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.Product;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String slug;
    private String description;

    private String image;

    private Double price;

    private ProductCategoryDto originCategory;

    private ProductCategoryDto genderCategory;

    private ProductCategoryDto brandCategory;

    private ProductCategoryDto typeCategory;

    private Set<ProductCategoryDto> ageCategory;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.slug = product.getSlug();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.originCategory = new ProductCategoryDto(product.getOriginCategory());
        this.genderCategory = new ProductCategoryDto(product.getGenderCategory());
        this.brandCategory = new ProductCategoryDto(product.getBrandCategory());
        this.typeCategory = new ProductCategoryDto(product.getTypeCategory());
        this.ageCategory = product.getAgeCategory().stream()
                .map(ProductCategoryDto::new)
                .collect(Collectors.toSet());
    }
}
