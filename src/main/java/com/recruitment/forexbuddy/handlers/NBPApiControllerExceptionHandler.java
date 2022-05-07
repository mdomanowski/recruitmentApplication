package com.recruitment.forexbuddy.handlers;

import com.recruitment.forexbuddy.exception.InvalidApiConnection;
import com.recruitment.forexbuddy.handlers.dtos.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NBPApiControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidApiConnectionExceptionHandler(InvalidApiConnection invalidApiConnectionException) {
        return ErrorMessage.builder()
                .errorDescription(invalidApiConnectionException.getMessage())
                .build();
    }
}
