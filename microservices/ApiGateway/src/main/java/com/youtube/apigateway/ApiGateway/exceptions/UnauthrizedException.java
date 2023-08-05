package com.youtube.apigateway.ApiGateway.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthrizedException extends RuntimeException {

    public UnauthrizedException(String message) {
        super(message);
    }
}
