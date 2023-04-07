package com.kopchak.worldoftoys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken extends Token{
    @Enumerated(EnumType.STRING)
    private ConfirmTokenType tokenType;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
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
