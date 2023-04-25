package com.kopchak.worldoftoys.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User data for login")
public class UserAuthDto {
    @Schema(example = "test@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(min = 3, max = 320, message = "Email must be up to 320 characters long")
    private String email;

    @Schema(example = "password1234", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Encoded password must be 60 characters long")
    private String password;
}
