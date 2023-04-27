package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.token.ConfirmTokenDto;
import com.kopchak.worldoftoys.dto.user.ResetPasswordDto;
import com.kopchak.worldoftoys.model.token.ConfirmTokenType;

public interface ConfirmationTokenService {
    ConfirmTokenDto createConfirmToken(String username, ConfirmTokenType tokenType);
    String confirmToken(String token);
    boolean isValidActivationTokenExists(String email);
    String confirmResetToken(String token, ResetPasswordDto newPassword);
}
