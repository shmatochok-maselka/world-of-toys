package com.kopchak.worldoftoys.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Set new password")
public class ChangePasswordDto {
    @Schema(example = "password1234", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Old password is mandatory")
    @Size(min = 8, max = 30, message = "Password must from 8 to 30 characters long")
    private String oldPassword;

    @Schema(example = "password12345", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "New password is mandatory")
    @Size(min = 8, max = 30, message = "Password must from 8 to 30 characters long")
    private String newPassword;
}
