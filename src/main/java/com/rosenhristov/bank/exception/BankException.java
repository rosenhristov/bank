package com.rosenhristov.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BankException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BankException(String msg) {
        super(msg);
    }

    public BankException(String msg, Throwable t) {
        super(msg, t);
    }

}
