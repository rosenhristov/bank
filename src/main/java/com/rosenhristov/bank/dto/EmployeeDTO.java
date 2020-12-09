package com.rosenhristov.bank.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Employee name must not be empty")
    private String name;

    private String midName;

    @NotNull(message = "Employee surname must not be empty")
    private String surname;

    @NotNull(message = "Employee position must not be empty")
    private String position;

    @NotNull(message = "Employee salary must not be empty")
    private BigInteger salary;

    @NotNull(message = "Employee phone number must not be empty")
    private String phone;

    @Email(regexp = "[\\w.-]+@[\\w.-]+.[.\\w-]+", message = "Email should be of pattern: 'username@domain.com'")
    private String email;

    @NotNull(message = "Employee address must not be empty")
    private AddressDTO address;

    @NotNull(message = "Employee's date of hiring must not be empty")
    private Date dateHired;

    @NotNull(message = "Employee's start of experience must not be empty")
    private Date startOfExperience;

    List<ClientDTO> clients;

    List<BankAccountDTO> accounts;
}
