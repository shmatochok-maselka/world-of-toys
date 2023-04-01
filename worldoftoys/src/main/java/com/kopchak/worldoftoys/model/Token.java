package com.kopchak.worldoftoys.model;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Token {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Token(String token, TokenType tokenType, User user) {
        this.token = token;
        this.tokenType = tokenType;
        this.user = user;
    }
}
