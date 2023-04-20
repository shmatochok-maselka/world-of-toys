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
public class UsernameDto {
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(min = 6, max = 320, message = "Email must be up to 320 characters long")
    private String email;
}
