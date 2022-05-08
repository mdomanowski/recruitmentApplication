package com.recruitment.forexbuddy.handlers;

import com.recruitment.forexbuddy.exception.InvalidAmountException;
import com.recruitment.forexbuddy.exception.InvalidApiConnection;
import com.recruitment.forexbuddy.exception.InvalidCurrencyException;
import com.recruitment.forexbuddy.handlers.dtos.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NBPApiControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse invalidApiConnectionExceptionHandler(InvalidApiConnection invalidApiConnectionException) {
        return ErrorMessageResponse.builder()
                .errorDescription(invalidApiConnectionException.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ErrorMessageResponse invalidCurrencyException(InvalidCurrencyException invalidCurrencyException) {
        return ErrorMessageResponse.builder()
                .errorDescription(invalidCurrencyException.getMessage())
                .build();
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse invalidAmountException(InvalidAmountException invalidAmountException) {
        return ErrorMessageResponse.builder()
                .errorDescription(invalidAmountException.getMessage())
                .build();
    }
}
