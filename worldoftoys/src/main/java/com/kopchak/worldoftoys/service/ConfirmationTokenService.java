package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.ConfirmTokenDto;
import com.kopchak.worldoftoys.dto.PasswordResetDto;
import com.kopchak.worldoftoys.model.token.ConfirmTokenType;

public interface ConfirmationTokenService {
    ConfirmTokenDto createConfirmToken(String username, ConfirmTokenType tokenType);
    String confirmToken(String token);
    boolean isValidActivationTokenExists(String email);
    String confirmResetToken(String token, PasswordResetDto newPassword);
}
