package com.dataart.bank.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction")
@ApiModel(description = "Model for bank transaction")
@EqualsAndHashCode
public class Transaction extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_Sequence")
    @SequenceGenerator(name = "transaction_Sequeence", sequenceName = "TRANSACTION_SEQ")
    @ApiModelProperty(name = "id", value = "the id of a trannsaction", dataType = "Long", example = "1")
    private Long id;

    @NotNull(message = "Transaction amount must not be empty")
    @Column(name = "amount")
    @ApiModelProperty(name = "amount", value = "the amount transferred with the current transaction",
                      dataType = "BigDecimal", example = "150.00")
    private BigDecimal amount;

    @NotNull(message = "Transaction sender must not be empty")
    @ManyToOne(targetEntity = BankAccount.class,
               fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                           CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "sender_id")
    @ApiModelProperty(name = "sender", value = "the bank account from which the transferred amount was withdrawn",
                      dataType = "BankAccount", example = "BankAccount(id:1,...}")
    private BankAccount sender;

    @NotNull(message = "Transaction receiver must not be empty")
    @ManyToOne(targetEntity = BankAccount.class,
               fetch = FetchType.LAZY,
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "receiver_id")
    @ApiModelProperty(name = "receiver", value = "the bank account in which the transferred amount was added",
                      dataType = "BankAccount", example = "BankAccount(id:21,...}")
    private BankAccount receiver;

}
