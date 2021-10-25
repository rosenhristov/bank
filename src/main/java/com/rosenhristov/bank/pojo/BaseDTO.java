package com.rosenhristov.bank.pojo;


import java.io.Serializable;
import java.sql.Date;

public class BaseDTO implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected Date dateCreated;

    protected Date dateUpdated;

}
