package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.dto.ConfirmTokenDto;

public interface ConfirmationTokenService {
    ConfirmTokenDto createConfirmToken(String username);
    String confirmToken(String token);
}
