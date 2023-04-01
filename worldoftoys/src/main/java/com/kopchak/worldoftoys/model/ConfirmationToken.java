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
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    public ConfirmationToken(String token, TokenType tokenType, User user,
                             LocalDateTime createdAt, LocalDateTime expiresAt) {
        super(token, tokenType, user);
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
