package com.rosenhristov.bank.dto;

import com.rosenhristov.bank.entity.AccountType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
public class BankAccountDTO {

    private Long id;

    @NotNull(message = "Account number must not be empty")
    private String accountNumber;

    @NotNull(message = "IBAN must not be empty")
    private String iban;

    @NotNull(message = "Account type must not be empty")
    private AccountType type;

    private Currency currency;

    @NotNull(message = "Balance must not be empty")
    private BigDecimal balance;

    protected ClientDTO client;

    private EmployeeDTO accountManager;
}
