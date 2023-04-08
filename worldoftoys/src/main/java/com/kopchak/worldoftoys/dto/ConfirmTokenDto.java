package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmTokenDto {
    private String token;

    public ConfirmTokenDto(ConfirmationToken confirmToken) {
        this.token = confirmToken.getToken();
    }
}
