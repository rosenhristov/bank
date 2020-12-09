package com.dataart.bank.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BankAppExceptionMapper implements ExceptionMapper<BankException> {

    public Response toResponse(BankException exception) {
        return Response
                .status(500)
                .entity("Bank exception occurred." + exception.getMessage())
                .type("text/plain")
                .build();
    }

}
