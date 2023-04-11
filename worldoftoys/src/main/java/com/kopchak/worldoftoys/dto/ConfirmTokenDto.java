package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.token.ConfirmationToken;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmTokenDto {
    @NonNull
    private String token;

    public ConfirmTokenDto(ConfirmationToken confirmToken) {
        this.token = confirmToken.getToken();
    }
}
