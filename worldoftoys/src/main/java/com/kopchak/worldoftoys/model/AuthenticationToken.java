package com.kopchak.worldoftoys.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AuthenticationToken extends Token{

    public boolean revoked;

    public boolean expired;

    public AuthenticationToken(String token, TokenType tokenType, User user, boolean revoked, boolean expired) {
        super(token, tokenType, user);
        this.revoked = revoked;
        this.expired = expired;
    }
}
