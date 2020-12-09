package com.dataart.bank.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ClientDTO {

    private Long id;

    @NotNull(message = "Client name must not be empty")
    private String name;

    private String midName;

    @NotNull(message = "Client surname must not be empty")
    private String surname;

    private String phone;

    @Email(regexp = "[\\w.-]+@[\\w.-]+.[.\\w-]+", message = "Email should be of pattern: 'username@domain.com'")
    private String email;

    private AddressDTO address;

    @NotNull(message = "ID card number must not be empty")
    private Long idCardNumber;

    @NotNull(message = "ID card issue date must not be empty")
    private Date idCardIssueDate;

    @NotNull(message = "ID card expiration date must not be empty")
    private Date idCardExpirationDate;

    @NotNull(message = "Bank accounts must not be empty")
    private List<BankAccountDTO> bankAccounts;

    private EmployeeDTO accountManager;

    private Long debitCardNumber;

    private Long creditCardNumber;
}
