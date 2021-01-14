package com.rosenhristov.bank.config;

import com.rosenhristov.bank.dto.BankAccountDTO;
import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.dto.TransactionDTO;
import com.rosenhristov.bank.entity.BankAccount;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.entity.Employee;
import com.rosenhristov.bank.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper getDozerMapper() {
        log.info("Initializing DozerBeanMapper bean");
        DozerBeanMapper dozerMapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
        dozerMapper.addMapping(getClientMappings());
        dozerMapper.addMapping(getEmployeeMappings());
        dozerMapper.addMapping(getBankAccountMappings());
        dozerMapper.addMapping(getTransactionMappings());
        return dozerMapper;
    }

    @Bean
    public BeanMappingBuilder getClientMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Client.class, ClientDTO.class)
                        .fields("id", "id")
                        .fields("name", "name")
                        .fields("midName", "midName")
                        .fields("surname", "surname")
                        .exclude("password")
                        .fields("phone", "phone")
                        .fields("email", "email")
                        .fields("address", "address")
                        .fields("idCardNumber", "idCardNumber")
                        .fields("idCardIssueDate", "idCardIssueDate")
                        .fields("idCardExpirationDate", "idCardExpirationDate")
                        .exclude("bankAccounts")
                        .exclude("accountManager")
                        .fields("debitCardNumber", "debitCardNumber")
                        .fields("creditCardNumber", "creditCardNumber")
                        .fields("dateCreated", "dateCreated")
                        .fields("dateUpdated", "dateUpdated");
            }
        };
    }


    @Bean
    public BeanMappingBuilder getEmployeeMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Employee.class, EmployeeDTO.class)
                        .fields("id", "id")
                        .fields("name", "name")
                        .fields("midName", "midName")
                        .fields("surname", "surname")
                        .exclude("password")
                        .fields("position", "position")
                        .fields("salary", "salary")
                        .fields("phone", "phone")
                        .fields("email", "email")
                        .fields("address", "address")
                        .fields("dateHired", "dateHired")
                        .fields("startOfExperience", "startOfExperience")
                        .fields("clients", "clients")
                        .fields("accounts", "accounts")
                        .fields("dateCreated", "dateCreated")
                        .fields("dateUpdated", "dateUpdated");
            }
        };
    }

    @Bean
    public BeanMappingBuilder getBankAccountMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BankAccount.class, BankAccountDTO.class)
                        .fields("id", "id")
                        .fields("accountNumber", "accountNumber")
                        .fields("iban", "iban")
                        .fields("type", "type")
                        .fields("currency", "currency")
                        .fields("balance", "balance")
                        .fields("client", "client")
                        .fields("accountManager", "accountManager")
                        .fields("dateCreated", "dateCreated")
                        .fields("dateUpdated", "dateUpdated");
            }
        };
    }

    @Bean
    public BeanMappingBuilder getTransactionMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Transaction.class, TransactionDTO.class)
                        .fields("id", "id")
                        .fields("amount", "amount")
                        .fields("sender", "sender")
                        .fields("receiver", "receiver")
                        .fields("dateCreated", "dateCreated")
                        .fields("dateUpdated", "dateUpdated");
            }
        };
    }
}
