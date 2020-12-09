package com.dataart.bank.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
@ApiModel(description = "The model for a bank employee")
@EqualsAndHashCode(callSuper=true)
public class Employee extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_Sequence")
    @SequenceGenerator(name = "employee_Sequence", sequenceName = "EMPLOYEE_SEQ")
    @Column(name= "id")
    @ApiModelProperty(name = "id", value = "the id of an employee", dataType = "Long", example = "1")
    private Long id;

    @NotNull(message = "Employee name must not be empty")
    @Column(name= "name")
    @ApiModelProperty(name = "name", value = "the name of an employee", dataType = "String", example = "Ivan")
    private String name;

    @Column(name= "mid_name")
    @ApiModelProperty(name = "midName", value = "the second name of an employee", dataType = "String", example = "Petrov")
    private String midName;

    @NotNull(message = "Employee surname must not be empty")
    @Column(name= "surname")
    @ApiModelProperty(name = "surname", value = "the last name of an employee", dataType = "String", example = "Dimitrov")
    private String surname;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,30}$",
             message = "Password must be 8 to 30 characters long and must include at least one of each of " +
                       "these: small letter, capital letter, number, special character")
    @NotNull(message = "Employee password must not be empty")
    @Column(name="password")
    @ApiModelProperty(name = "password", value = "the password of an employee", dataType = "String", example = ":P@ssw0rd!")
    private String password;

    @NotNull(message = "Employee position must not be empty")
    @Column(name= "position")
    @ApiModelProperty(name = "position", value = "the position of an employee in the bank", dataType = "String", example = "Managing Director")
    private String position;

    @NotNull(message = "Employee salary must not be empty")
    @Column(name= "salary")
    @ApiModelProperty(name = "salary", value = "the salary of an employee", dataType = "String", example = ":P@ssw0rd!")
    private BigInteger salary;

    @NotNull(message = "Employee phone number must not be empty")
    @Column(name= "phone")
    @ApiModelProperty(name = "phone", value = "the phone of an employee", dataType = "String", example = "+359888667788")
    private String phone;

    @Email(regexp = "[\\w.-]+@[\\w.-]+.[.\\w-]+", message = "Email should be of pattern: 'username@domain.com'")
    @Column(name = "email")
    @ApiModelProperty(name = "email", value = "the email of an employee",
                      dataType = "String", example = "username@company.com")
    private String email;

    @NotNull(message = "Employee address must not be empty")
    @Column(name= "address")
    @ApiModelProperty(name = "address", value = "the addresss of an employee",
                      dataType = "String", example = "1000 Sofia, 10 Dondoukov Str.")
    private Address address;

    @NotNull(message = "Employee's date of hiring must not be empty")
    @Column(name = "date_hired")
    @ApiModelProperty(name = "dateHired", value = "the date the employeee was hired in the bank",
                      dataType = "Date", example = "2020-01-01")
    private Date dateHired;

    @NotNull(message = "Employee's start of experience must not be empty")
    @Column(name= "start_of_experience")
    @ApiModelProperty(name = "startOfExperience", value = "the date when the employee started working experience",
                      dataType = "String", example = "2000-01-01")
    private Date startOfExperience;

    @OneToMany(fetch = FetchType.EAGER,
               mappedBy="accountManager",
               cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @ApiModelProperty(name = "clients", value = "the list of clients the employee is personally responsible for, e.g. as account manager or personal banker",
                      dataType = "List", example = "{client1, cient2, client3, ...}")
    List<Client> clients;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy="accountManager",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @ApiModelProperty(name = "accounts", value = "list of accounts the employee manages as account manager if such",
            dataType = "List", example = "{account1, account2, account3, ...}")
    List<BankAccount> accounts;

    @Override
    protected void doPreRemove() {
        setAccounts(null);
        setClients(null);
    }
}
