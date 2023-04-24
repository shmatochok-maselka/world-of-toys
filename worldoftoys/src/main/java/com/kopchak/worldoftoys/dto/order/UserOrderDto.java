package com.kopchak.worldoftoys.dto.order;

import com.kopchak.worldoftoys.dto.CartItemResponseDto;
import com.kopchak.worldoftoys.model.order.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDto {
    private LocalDateTime dateTime;
    private OrderStatus status;
    private BigDecimal totalPrice;
    Set<CartItemResponseDto> products;
}
