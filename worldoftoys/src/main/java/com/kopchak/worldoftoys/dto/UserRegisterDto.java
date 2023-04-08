package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRegisterDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean enabled;
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
