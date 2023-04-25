package com.kopchak.worldoftoys.model.token;

import com.kopchak.worldoftoys.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken extends Token {
    @Enumerated(EnumType.STRING)
    @NonNull
    private ConfirmTokenType tokenType;
    @Column(nullable = false)
    @NonNull
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    public ConfirmationToken(String token, ConfirmTokenType tokenType, User user,
                             LocalDateTime createdAt, LocalDateTime expiresAt) {
        super(token, user);
        this.tokenType = tokenType;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
