package com.dataart.bank.utils;

import java.time.Instant;

public class Utils {

    public static java.sql.Date sqlDateNow() {
        return new java.sql.Date(Instant.now().toEpochMilli());
    }

    public static java.util.Date dateNow() {
        return new java.util.Date(Instant.now().toEpochMilli());
    }

    public static java.util.Date toDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Date toSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}
