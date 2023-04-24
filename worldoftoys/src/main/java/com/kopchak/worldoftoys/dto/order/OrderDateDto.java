package com.kopchak.worldoftoys.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDateDto {
    private LocalDateTime orderDateTime;
}
