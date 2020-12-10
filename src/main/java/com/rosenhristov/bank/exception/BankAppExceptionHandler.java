package com.rosenhristov.bank.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
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
        String exception = String.format("Exception occurred: %s\n", e.getClass().getSimpleName());
        String message = String.format("Exception Message:\n%s\n", e.getMessage());
        String stacktrace = String.format("Exception stack trace:\n%s", stackTraceToString(e));
        String description = new StringBuilder(exception)
                                .append(message)
                                .append(stacktrace)
                                .toString();
        log.error(exception + message, e);
        return description;
    }

    private String stackTraceToString(Exception e) {
        StringBuilder builder = new StringBuilder();
        for (StackTraceElement el : e.getStackTrace()) {
            builder.append(el.toString()).append("\n");
        }
        return builder.toString();
    }
}
