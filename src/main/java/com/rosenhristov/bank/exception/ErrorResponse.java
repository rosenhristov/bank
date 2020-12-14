package com.rosenhristov.bank.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {

    private String httpStatus;
    private String exception;
    private String message;
    private String[] stackTrace;

    public ErrorResponse(HttpStatus httpStatus, Throwable e) {
        this.httpStatus = convertStatusToString(httpStatus);
        this.exception = e.getClass().getName();
        this.message = e.getMessage();
        this.stackTrace = convertStackTraceToStringArray(e);
    }

    private String convertStatusToString(HttpStatus httpStatus) {
        return new StringBuilder()
                        .append(httpStatus.value())
                        .append(" ")
                        .append(httpStatus.getReasonPhrase())
                        .toString();
    }

    private String[] convertStackTraceToStringArray(Throwable e) {
        String[] array= new String[e.getStackTrace().length];
        for (int i = 0; i < e.getStackTrace().length; i++) {
            array[i] = e.getStackTrace()[i].toString();
        }
        return array;
    }
}
