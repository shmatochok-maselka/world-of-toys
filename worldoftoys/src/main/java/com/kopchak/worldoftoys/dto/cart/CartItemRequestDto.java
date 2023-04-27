package com.kopchak.worldoftoys.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDto {
    @Schema(description = "Unique product identifier in a URL", example = "teddy-bear", required = true)
    @NotBlank(message = "Slug is mandatory")
    @Size(min = 3, max = 80, message = "Slug must be up to 80 characters long")
    private String slug;

    @Schema(description = "Quantity of product in the cart", example = "1")
    @Builder.Default
    @NonNull
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private Integer quantity = 1;
}
