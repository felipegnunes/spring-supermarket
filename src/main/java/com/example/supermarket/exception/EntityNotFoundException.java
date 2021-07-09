package com.example.supermarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class EntityNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Resource not found";
    }
}
