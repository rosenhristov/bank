package com.rosenhristov.bank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Employee extends BaseDTO {

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
    private String address;

    @NotNull(message = "Employee's date of hiring must not be empty")
    private Date dateHired;

    @NotNull(message = "Employee's start of experience must not be empty")
    private Date startOfExperience;

    List<Client> clients;

    List<BankAccount> accounts;
}
