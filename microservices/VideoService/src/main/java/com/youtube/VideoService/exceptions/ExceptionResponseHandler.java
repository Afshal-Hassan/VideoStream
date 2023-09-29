package com.youtube.VideoService.exceptions;


import com.youtube.VideoService.payloads.global_domains.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@RestController
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {


    /**
     * {@code Rest Controller Exception } : Handle all exceptions.
     *
     * @param ex      to handle exception, with type {@link Exception} ,
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 500 (Internal Server Error)}.  ,
     */
    @ExceptionHandler(Exception.class) // this will handle all types of exception
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@code Rest Controller Exception } : Handle http message not readable.
     *
     * @param ex      to handle exception, with type {@link HttpMessageNotReadableException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 400 (Bad Request)}
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed",
                ex.getLocalizedMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * {@code Rest Controller Exception } : Handle method argument not valid.
     *
     * @param ex      to handle exception, with type {@link MethodArgumentNotValidException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 400 (Bad Request)}
     */
    @Override // overriding to return custom response when validations fail
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, String> res = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        error -> {
                            String fieldName = ((FieldError) error).getField();
                            String message = error.getDefaultMessage();
                            res.put(fieldName, message);
                        });
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), "Validation Failed: " + res, request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * {@code Rest Controller Exception } : Handle invalid annotations usage.
     *
     * @param ex      to handle exception, with type {@link InvalidAnnotationException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 500 (Internal Server Error)}
     */
    @ExceptionHandler(InvalidAnnotationException.class)
    protected ResponseEntity<Object> handleInvalidAnnotation(
            InvalidAnnotationException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@code Rest Controller Exception } : Handle not found exception.
     *
     * @param ex      to handle exception, with type {@link NotFoundException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 404 (Not Found)}
     */
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * {@code Rest Controller Exception } : Handle bad request exception.
     *
     * @param ex      to handle exception, with type {@link BadRequestException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 400 (Bad Request)}
     */
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * {@code Rest Controller Exception } : Handle internal server exception.
     *
     * @param ex      to handle exception, with type {@link InternalServerException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 500 (Internal Server Exception)}
     */
    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<Object> handleInternalServerException(
            InternalServerException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * {@code Rest Controller Exception } : Handle internal server exception.
     *
     * @param ex      to handle exception, with type {@link UnauthorizedException} ,
     * @param headers to handle http headers, with type {@link HttpHeaders},
     * @param status  to handle http status code, with type {@link HttpStatusCode}
     * @param request to handle web request, with type {@link WebRequest} ,
     * @return the {@link ResponseEntity}, with the Http status {@code 403 (Forbidden)}
     */
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedException(
            UnauthorizedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
