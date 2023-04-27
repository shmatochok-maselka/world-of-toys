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
public class UsernameDto {
    @Schema(example = "test@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(min = 6, max = 320, message = "Email must be up to 320 characters long")
    private String email;
}
