package com.kopchak.worldoftoys.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDto {
    private String name;
    private String slug;
    private String image;
    private BigDecimal price;
    private Integer quantity;
}
