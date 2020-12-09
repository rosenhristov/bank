package com.dataart.bank.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private int postalCode;
    private String city;
    private String street;
    private int streetNumber;
    private String country;

}