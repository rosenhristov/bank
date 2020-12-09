package com.dataart.bank.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDTO {

    private Long id;

    @NotNull(message = "Transaction amount must not be empty")
    private BigDecimal amount;

    @NotNull(message = "Transaction sender must not be empty")
    private BankAccountDTO sender;

    @NotNull(message = "Transaction receiver must not be empty")
    private BankAccountDTO receiver;

    @NotNull(message = "Transaction's date of creation must not be empty")
    private Date createdAt;
}
