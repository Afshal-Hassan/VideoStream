package com.youtube.auth.service.AuthService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSavedException extends RuntimeException {

    public NotSavedException(String message) {
        super(message);
    }
}
