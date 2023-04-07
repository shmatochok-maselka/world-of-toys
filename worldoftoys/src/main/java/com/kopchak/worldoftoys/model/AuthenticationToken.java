package com.kopchak.worldoftoys.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class AuthenticationToken extends Token{
    @Enumerated(EnumType.STRING)
    public AuthTokenType tokenType;
    public boolean revoked;

    public boolean expired;

    public AuthenticationToken(String token, User user, AuthTokenType tokenType, boolean revoked, boolean expired) {
        super(token, user);
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
    }
}
