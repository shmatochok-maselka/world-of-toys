package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public UserDto(User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User toUser(){
        return User.builder()
                .firstname(this.getFirstname())
                .lastname(this.getLastname())
                .email(this.getEmail())
                .password(this.getPassword())
                .build();
    }
}
