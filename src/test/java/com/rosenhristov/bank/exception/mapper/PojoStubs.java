package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.entity.ClientEntity;
import com.rosenhristov.bank.pojo.Client;

import java.sql.Date;

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
                Date.valueOf("2030-07-01"), null,
                null,
                1324657980L,
                1176453120L);
    }

}
