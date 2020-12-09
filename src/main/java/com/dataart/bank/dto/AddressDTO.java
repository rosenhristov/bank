package com.dataart.bank.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private int postalCode;
    private String city;
    private String street;
    private int streetNumber;
    private String country;
}
