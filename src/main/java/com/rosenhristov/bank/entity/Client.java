package com.rosenhristov.bank.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "client")
@ApiModel(description = "The model for a bank customer")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_Sequence")
    @SequenceGenerator(name = "client_Sequence", sequenceName = "CLIENT_SEQ")
    @Column(name = "id")
    @ApiModelProperty(name = "id", value = "the id of a client", dataType = "Long", example = "1")
    private Long id;

    @NotNull(message = "Client name must not be empty")
    @Column(name = "name")
    @ApiModelProperty(name = "name", value = "the name of a client", dataType = "String", example = "Ivan")
    private String name;

    @Column(name = "mid_name")
    @ApiModelProperty(name = "midName", value = "the second name of a client", dataType = "String", example = "Petrov")
    private String midName;

    @NotNull(message = "Client surname must not be empty")
    @Column(name = "surname")
    @ApiModelProperty(name = "surname", value = "the last name of a client", dataType = "String", example = "Dimitrov")
    private String surname;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,30}$",
             message = "Password must be 8 to 30 characters long and must include at least one of each of " +
                       "these: small letter, capital letter, number, special character")
    @NotNull(message = "Client password must not be empty")
    @Column(name = "password")
    @ApiModelProperty(name = "password", value = "the password of an employee", dataType = "String", example = ":P@ssw0rd!")
    private String password;

    @Column(name = "phone")
    @ApiModelProperty(name = "phone", value = "the phone of a client", dataType = "String", example = "+359888667788")
    private String phone;

    @Email(regexp = "[\\w.-]+@[\\w.-]+.[.\\w-]+", message = "Email should be of pattern: 'username@domain.com'")
    @Column(name = "email")
    @ApiModelProperty(name = "email", value = "the email of a client", dataType = "String", example = "username@company.com")
    private String email;

    @NotNull(message = "Client address must not be empty")
    @Column(name = "address")
    @ApiModelProperty(name = "address", value = "the addresss of a client", dataType = "String",
            example = "1000 Sofia, 10 Dondoukov Str.")
    private String address;

    @NotNull(message = "ID card number must not be empty")
    @Column(name = "id_card_number")
    @ApiModelProperty(name = "idCardNumber", value = "the ID/passport number of a client", dataType = "String",
            example = "64012345678")
    private Long idCardNumber;

    @NotNull(message = "ID card issue date must not be empty")
    @Column(name = "id_card_isssue_date")
    @ApiModelProperty(name = "idCardIssueDate", value = "a client's ID/passport issue date",
                      dataType = "Date", example = "2020-07-01")
    private Date idCardIssueDate;

    @NotNull(message = "ID card expiration date must not be empty")
    @Column(name = "id_card_expiration_date")
    @ApiModelProperty(name = "idCardExpirationDate", value = "a client's ID/passport expiration date",
                      dataType = "Date", example = "2030-06-30")
    private Date idCardExpirationDate;

    @NotNull(message = "Bank accounts must not be empty")
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy="client",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                       CascadeType.DETACH, CascadeType.REFRESH})
    @ApiModelProperty(name = "bankAccounts", value = "a client's list of bank accounts",
                      dataType = "List<BankAccount>", example = "{account1, account2, account3,...}")
    private List<BankAccount> bankAccounts;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="employee_id")
    @ApiModelProperty(name = "accountManager", value = "a client's account manager (bank employee)",
                      dataType = "Employee", example = "Employee{id:10,...}")
    private Employee accountManager;

    @Column(name = "debit_card_number")
    @ApiModelProperty(name = "debitCardNumber", value = "a client's debit card number",
                      dataType = "Long", example = "5220479568150000")
    private Long debitCardNumber;

    @Column(name = "credit_card_number")
    @ApiModelProperty(name = "creditCardNumber", value = "a client's credit card number",
                      dataType = "Long", example = "5220479568150000")
    private Long creditCardNumber;

    @Override
    protected void doPreRemove() {
        setAccountManager(null);
        setBankAccounts(null);
    }
}
