package com.kopchak.worldoftoys.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenAuthDto {
    @NonNull
    private String token;
}
