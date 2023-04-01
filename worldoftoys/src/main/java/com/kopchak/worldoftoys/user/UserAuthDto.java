package com.kopchak.worldoftoys.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAuthDto {
    private String email;
    private String password;
}
