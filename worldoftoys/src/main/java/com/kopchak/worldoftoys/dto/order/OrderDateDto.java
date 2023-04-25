package com.kopchak.worldoftoys.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDateDto {
    @Schema(example = "2023-04-25T12:35:25.008152", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime orderDateTime;
}
