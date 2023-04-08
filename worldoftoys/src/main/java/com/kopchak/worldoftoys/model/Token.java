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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    public User user;

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
