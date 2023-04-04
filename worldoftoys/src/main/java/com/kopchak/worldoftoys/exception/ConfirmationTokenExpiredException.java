package com.kopchak.worldoftoys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConfirmationTokenExpiredException extends ResponseStatusException {
    public ConfirmationTokenExpiredException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
