package com.kopchak.worldoftoys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kopchak.worldoftoys.model.User;
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
@Schema(description = "User data for registration")
public class UserRegisterDto {
    @Schema(example = "Firstname", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 3, max = 60, message = "Firstname must be up to 60 characters long")
    private String firstname;

    @Schema(example = "Lastname", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 60, message = "Lastname must be up to 60 characters long")
    private String lastname;
    @Schema(example = "test@test.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(min = 6, max = 320, message = "Email must be up to 320 characters long")
    private String email;

    @Schema(example = "password1234", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 30, message = "Password must from 8 to 30 characters long")
    private String password;
    @JsonIgnore
    private Boolean enabled;
    @JsonIgnore
    private Boolean locked;

    public UserRegisterDto(User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
        this.locked = user.getLocked();
    }

    public User toUser() {
        return User.builder()
                .firstname(this.getFirstname())
                .lastname(this.getLastname())
                .email(this.getEmail())
                .password(this.getPassword())
                .enabled(false)
                .locked(false)
                .build();
    }
}
