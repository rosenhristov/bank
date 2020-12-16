package com.rosenhristov.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class TransactionDTO extends BaseDTO {

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
