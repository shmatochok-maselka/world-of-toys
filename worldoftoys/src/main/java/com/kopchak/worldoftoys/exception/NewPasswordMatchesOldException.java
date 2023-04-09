package com.kopchak.worldoftoys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NewPasswordMatchesOldException extends ResponseStatusException {
    public NewPasswordMatchesOldException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
