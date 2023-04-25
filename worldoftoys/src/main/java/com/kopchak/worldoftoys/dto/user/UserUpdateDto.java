package com.kopchak.worldoftoys.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @Schema(example = "Iryna", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(min = 3, max = 60, message = "Firstname must be up to 60 characters long")
    private String firstname;

    @Schema(example = "Kopchak", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(min = 3, max = 60, message = "Lastname must be up to 60 characters long")
    private String lastname;
}
