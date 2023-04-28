package com.kopchak.worldoftoys.dto.product;

import com.kopchak.worldoftoys.model.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductShopDto {
    @Schema(example = "product", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Product name is mandatory")
    @Size(min = 3, max = 60, message = "Name must be up to 60 characters long")
    private String name;

    @Schema(example = "product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(min = 3, max = 80, message = "Slug must be up to 80 characters long")
    private String slug;

    @Schema(example = "description", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Description is mandatory")
    @Size(max = 250, message = "Description must be up to 250 characters long")
    private String description;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(max = 200, message = "Image must be up to 200 characters long")
    private String image;

    @Schema(example = "1999.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    @NotNull(message = "Origin category is mandatory")
    private ProductCategoryDto originCategory;

    @NotNull(message = "Gender category is mandatory")
    private ProductCategoryDto genderCategory;

    @NotNull(message = "Brand category is mandatory")
    private ProductCategoryDto brandCategory;

    @NotNull(message = "Type category is mandatory")
    private ProductCategoryDto typeCategory;

    @NotNull(message = "Age categories is mandatory")
    private Set<ProductCategoryDto> ageCategory;

    public ProductShopDto(Product product) {
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
