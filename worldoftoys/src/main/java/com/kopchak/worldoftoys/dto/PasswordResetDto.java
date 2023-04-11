package com.kopchak.worldoftoys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDto {
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must from 8 to 30 characters long")
    private String password;
}
