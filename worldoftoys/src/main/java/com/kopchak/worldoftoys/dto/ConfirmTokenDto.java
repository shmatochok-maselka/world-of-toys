package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
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
