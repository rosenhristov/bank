package com.rosenhristov.bank.pojo;

import com.rosenhristov.bank.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BankAccount extends BaseDTO {

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

    protected Client client;

    private Employee accountManager;
}
