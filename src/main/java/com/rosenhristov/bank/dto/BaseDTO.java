package com.rosenhristov.bank.dto;


import java.io.Serializable;
import java.sql.Date;

public class BaseDTO implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected Date dateCreated;

    protected Date dateUpdated;

}
