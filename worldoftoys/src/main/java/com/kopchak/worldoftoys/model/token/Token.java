package com.kopchak.worldoftoys.model.token;

import com.kopchak.worldoftoys.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true)
    @NonNull
    public String token;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @NonNull
    public User user;

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
