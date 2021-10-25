package com.rosenhristov.bank.pojo;

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
public class Transaction extends BaseDTO {

    private Long id;

    @NotNull(message = "Transaction amount must not be empty")
    private BigDecimal amount;

    @NotNull(message = "Transaction sender must not be empty")
    private BankAccount sender;

    @NotNull(message = "Transaction receiver must not be empty")
    private BankAccount receiver;

    @NotNull(message = "Transaction's date of creation must not be empty")
    private Date createdAt;

}
