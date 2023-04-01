package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User toUser() {
        return User.builder()
                .firstname(this.getFirstname())
                .lastname(this.getLastname())
                .email(this.getEmail())
                .password(this.getPassword())
                .build();
    }
}
