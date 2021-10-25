package com.rosenhristov.bank.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class ErrorResponse {

    private String httpStatus;
    private String exception;
    private String message;
    private List<String> stacktrace;

    public ErrorResponse(HttpStatus httpStatus, Throwable e) {
        this.httpStatus = convertStatusToString(httpStatus);
        this.exception = e.getClass().getName();
        this.message = e.getMessage();
        this.stacktrace = convertStackTraceToStringList(e);
    }

    private String convertStatusToString(HttpStatus httpStatus) {
        return new StringBuilder()
                        .append(httpStatus.value())
                        .append(" ")
                        .append(httpStatus.getReasonPhrase())
                        .toString();
    }

    private List<String> convertStackTraceToStringList(Throwable e) {
        StackTraceElement[] stacktrace = e.getStackTrace();
        List<String> stacktraceString= new ArrayList(stacktrace.length);
        for (int i = 0; i < stacktrace.length; i++) {
            stacktraceString.add(stacktrace[i].toString());
        }
        return stacktraceString;
    }

    public String convertStacktraceStringListToString() {
        StringBuilder stacktraceStringBuilder = new StringBuilder();
        stacktrace.stream().forEach(el -> stacktraceStringBuilder.append(el + "\n"));
        return stacktraceStringBuilder.toString();
    }

    public void log() {
        log.error(this.toString());
    }

    @Override
    public String toString() {
        return new StringBuilder(String.format("::: Exception occurred: %s\n", httpStatus))
                .append(String.format("::: HTTP Status: %s\n", httpStatus))
                .append(String.format("::: Message ::: \n%s\n", message))
                .append(String.format("::: Stacktrace ::: \n%s\n", convertStacktraceStringListToString()))
                .toString();
    }
}
