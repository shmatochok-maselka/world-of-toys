package com.kopchak.worldoftoys.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@Entity
public class AuthenticationToken extends Token{
    @Enumerated(EnumType.STRING)
    @NonNull
    private AuthTokenType tokenType;
    @NonNull
    private boolean revoked;

    @NonNull
    private boolean expired;

    public AuthenticationToken(String token, User user, AuthTokenType tokenType, boolean revoked, boolean expired) {
        super(token, user);
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
    }
}
