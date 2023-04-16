package com.kopchak.worldoftoys.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDTO {
    @NotBlank(message = "Slug is mandatory")
    @Size(min = 3, max = 80, message = "Slug must be up to 80 characters long")
    private String slug;

    @Builder.Default
    @NonNull
    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private Integer quantity = 1;
}
