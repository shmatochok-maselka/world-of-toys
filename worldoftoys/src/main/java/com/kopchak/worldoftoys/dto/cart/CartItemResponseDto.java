package com.kopchak.worldoftoys.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representation of the product in the user's cart")
public class CartItemResponseDto {
    private String name;
    private String slug;
    private String image;
    private BigDecimal price;
    private BigInteger quantity;
}
