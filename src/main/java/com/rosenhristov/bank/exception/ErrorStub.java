package com.rosenhristov.bank.exception;

import lombok.Data;

@Data
public class ErrorStub {

    private Integer errorCode;

    private String errorDesc;

    public ErrorStub() {
    }

    public ErrorStub(Integer errorCode, String errorDesc) {
        super();
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}
