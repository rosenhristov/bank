package com.rosenhristov.bank.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@Entity
@Table(name = "bank_account")
@ApiModel(description = "Model for bank account")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BankAccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_Sequence")
    @SequenceGenerator(name = "account_Sequeence", sequenceName = "BANK_ACCOUNT_SEQ")
    @Column(name ="id")
    @ApiModelProperty(name = "id", value = "Bank account's id in the bank database", dataType = "Long", example = "1")
    private Long id;

    @NotNull(message = "Account number must not be empty")
    @Column(name ="account_number")
    @ApiModelProperty(name = "accountNumber", value = "a bank account's number", dataType = "String", example = "70001522489")
    private String accountNumber;

    @NotNull(message = "IBAN must not be empty")
    @Column(name ="iban")
    @ApiModelProperty(name = "iban", value = "a bank account's IBAN", dataType = "String", example = "BG51BANK70001522489")
    private String iban;

    @NotNull(message = "Account type must not be empty")
    @Enumerated
    @Column(name ="type")
    @ApiModelProperty(name = "type", value = "bank account's type: e.g. payment, deposit, budget, etc.",
                      dataType = "AccountType", example = "deposit")
    private AccountType type;

    @Column(name ="currency")
    @ApiModelProperty(name = "currency", value = "bank account's currency", dataType = "Currency", example = "BGN")
    private Currency currency;

    @NotNull(message = "Balance must not be empty")
    @Column(name ="balance")
    @ApiModelProperty(name = "balance", value = "bank account's balance(current amount)",
                      dataType = "BigDecimal", example = "10,000.00")
    private BigDecimal balance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="client_id")
    @ApiModelProperty(name = "client", value = "the client that owns this bank account",
                      dataType = "Client", example = "Client{\"name\":\"Ivan Ivanov\", ...}")
    protected ClientEntity clientEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="employee_id")
    @ApiModelProperty(name = "accountManager", value = "the bank employee serving as an account manager for the current account",
                      dataType = "Employee", example = "Employee{\"name\":\"Peter Dimitrov\", ...}")
    private EmployeeEntity accountManager;

    @Override
    protected void doPreRemove() {
        setClientEntity(null);
        setAccountManager(null);
    }
}
