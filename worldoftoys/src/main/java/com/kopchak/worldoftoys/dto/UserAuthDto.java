package com.kopchak.worldoftoys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(min = 3, max = 320, message = "Email must be up to 320 characters long")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Encoded password must be 60 characters long")
    private String password;
}
