package com.rosenhristov.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankAppExceptionHandler {

    @ExceptionHandler(BankException.class)
    public ResponseEntity<ErrorResponse> handleBankException(BankException e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), buildDescription(e)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), buildDescription(e)),
                HttpStatus.BAD_REQUEST);
    }

    private String buildDescription(Exception e) {
        return String.format("%s occurred.\nMessage: %s.\nStack trace:\n%s",
                e.getClass().getSimpleName(),
                e.getMessage(),
                e.getStackTrace().toString());
    }
}
