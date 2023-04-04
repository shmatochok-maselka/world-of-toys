package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.model.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    int setConfirmedAt(String token);

    ConfirmationToken createConfirmToken(String username);
}
