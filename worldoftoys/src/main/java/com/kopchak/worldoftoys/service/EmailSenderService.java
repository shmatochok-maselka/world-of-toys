package com.kopchak.worldoftoys.service;

import com.kopchak.worldoftoys.model.ConfirmTokenType;
import com.kopchak.worldoftoys.model.ConfirmationToken;

public interface EmailSenderService {
    void send(String to, String email);
    void sendConfirmEmail(String userEmail, String userFirstname, String confirmToken, ConfirmTokenType tokenType);
}
