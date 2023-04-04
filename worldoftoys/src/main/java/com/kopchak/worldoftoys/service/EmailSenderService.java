package com.kopchak.worldoftoys.service;

public interface EmailSenderService {
    void send(String to, String email);
    void sendConfirmEmail(String userEmail, String userFirstname, String confirmToken);
}
