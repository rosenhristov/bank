package com.rosenhristov.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Client extends BaseDTO {

    private Long id;

    @NotNull(message = "Client name must not be empty")
    private String name;

    private String midName;

    @NotNull(message = "Client surname must not be empty")
    private String surname;

    private String phone;

    @Email(regexp = "[\\w.-]+@[\\w.-]+.[.\\w-]+", message = "Email should be of pattern: 'username@domain.com'")
    private String email;

    @NotNull(message = "Client address must not be empty")
    private String address;

    @NotNull(message = "ID card number must not be empty")
    private Long idCardNumber;

    @NotNull(message = "ID card issue date must not be empty")
    private Date idCardIssueDate;

    @NotNull(message = "ID card expiration date must not be empty")
    private Date idCardExpirationDate;

    @NotNull(message = "Bank accounts must not be empty")
    private List<BankAccount> bankAccounts;

    private Employee accountManager;

    private Long debitCardNumber;

    private Long creditCardNumber;
}
