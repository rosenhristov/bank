package com.dataart.bank.exception;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BankAppExceptionHandler extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<>(
                List.of(
                    BankException.class,
                    NullPointerException.class
                )
        );
    }

}
