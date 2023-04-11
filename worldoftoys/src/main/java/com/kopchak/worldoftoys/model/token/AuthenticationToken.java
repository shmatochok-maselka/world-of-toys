package com.kopchak.worldoftoys.model.token;

import com.kopchak.worldoftoys.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AuthenticationToken extends Token {
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
