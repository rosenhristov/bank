package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.entity.AccountType;
import com.rosenhristov.bank.entity.ClientEntity;
import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.pojo.Client;
import com.rosenhristov.bank.pojo.Employee;
import com.rosenhristov.bank.pojo.Transaction;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Currency;
import java.util.List;

public class PojoStubs {

    static ClientEntity buildClientEntity() {
        return new ClientEntity(50L,
                "Ross",
                "F.",
                "Morris",
                "P@ss0rd12345678",
                "0889679972",
                "ross@domain.com",
                "10 'Asen Bosev' Str., 1000 Sofia, Bulgaria",
                1131011L,
                Date.valueOf("2020-07-01"),
                Date.valueOf("2030-07-01"),
                null,
                null,
                1324657980L,
                1176453120L);
    }

    static Client buildClient() {
        return new Client(50L,
                "Ross",
                "F.",
                "Morris",
                "0889679972",
                "ross@domain.com",
                "10 'Asen Bosev' Str., 1000 Sofia, Bulgaria",
                1131011L,
                Date.valueOf("2020-07-01"),
                Date.valueOf("2030-07-01"),
                null,
                null,
                1324657980L,
                1176453120L);
    }

    static Transaction buildTransaction() {
        return new Transaction(344L,
                BigDecimal.valueOf(5000),
                new BankAccount(),
                buildBankAccount(),
                Date.valueOf("2021-10-25"));
    }

    static BankAccount buildBankAccount() {
        return new BankAccount(1L,
                "7000015223565",
                "BG50ALIANZ195010BGN100BGB",
                AccountType.PAYMENT,
                Currency.getInstance("BGN"),
                BigDecimal.valueOf(10000),
                buildClient(),
                buildEmployee()
        );
    }

    static Employee buildEmployee() {
        return new Employee(55L,
                "Maria",
                "T.",
                "Hristova",
                "private banker",
                BigDecimal.valueOf(3500),
                "0898679012",
                "mhristova@alianz.bg",
                "1000 Sofia, bul. Todor Aleksandrov 28",
                Date.valueOf("26-10-2021"),
                Date.valueOf("01-08-2010"),
                List.of (buildClient()),
                List.of(buildBankAccount())
        );
    }

}
