package com.kopchak.worldoftoys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BlogPostNotFoundException extends ResponseStatusException {
    public BlogPostNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
