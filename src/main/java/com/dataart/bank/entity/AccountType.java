package com.dataart.bank.entity;


public enum AccountType {
    PAYMENT("payment"),
    DEPOSIT("deposit"),
    BUDGET("budget"),
    SAVING("saving"),
    BROKERAGE("brokerage"),
    RETIREMENT("retirement");

    private String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
