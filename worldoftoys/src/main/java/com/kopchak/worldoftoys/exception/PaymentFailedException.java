package com.kopchak.worldoftoys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentFailedException  extends ResponseStatusException {
    public PaymentFailedException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
