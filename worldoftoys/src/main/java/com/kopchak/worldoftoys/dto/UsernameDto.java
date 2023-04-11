package com.kopchak.worldoftoys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsernameDto {
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
}
