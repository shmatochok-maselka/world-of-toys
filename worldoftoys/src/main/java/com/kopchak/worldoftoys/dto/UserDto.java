package com.kopchak.worldoftoys.dto;

import com.kopchak.worldoftoys.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
