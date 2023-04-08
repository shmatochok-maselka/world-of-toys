package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.ConfirmTokenDto;
import com.kopchak.worldoftoys.model.ConfirmTokenType;
import com.kopchak.worldoftoys.model.User;

public interface ConfirmationTokenService {
    ConfirmTokenDto createConfirmToken(String username, ConfirmTokenType tokenType);
    String confirmToken(String token);
    boolean isValidActivationTokenExists(String email);
//    ConfirmTokenDto createResetPasswordToken(String username);
    String confirmResetToken(String token, String newPassword);
}
