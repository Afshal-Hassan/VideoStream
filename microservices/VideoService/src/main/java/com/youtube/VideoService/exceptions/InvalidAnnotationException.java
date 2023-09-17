package com.youtube.VideoService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidAnnotationException extends RuntimeException {

    public InvalidAnnotationException(String message) {
        super(message);
    }
}
