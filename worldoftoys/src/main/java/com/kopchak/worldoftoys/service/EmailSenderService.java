package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.model.token.ConfirmTokenType;

public interface EmailSenderService {
    void send(String to, String email);
    void sendConfirmEmail(String userEmail, String userFirstname, String confirmToken, ConfirmTokenType tokenType);
}
