package com.rosenhristov.bank.config;

import com.rosenhristov.bank.entity.TransactionEntity;
import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.pojo.Client;
import com.rosenhristov.bank.pojo.Employee;
import com.rosenhristov.bank.pojo.Transaction;
import com.rosenhristov.bank.entity.BankAccountEntity;
import com.rosenhristov.bank.entity.ClientEntity;
import com.rosenhristov.bank.entity.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DozerConfig {

    @Bean("dozerBeanMapper")
    public DozerBeanMapper buildDozerMapper() {
        log.info("Initializing DozerBeanMapper bean");

        DozerBeanMapper dozerMapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
        dozerMapper.addMapping(buildClientMappings());
        dozerMapper.addMapping(buildEmployeeMappings());
        dozerMapper.addMapping(buildBankAccountMappings());
        dozerMapper.addMapping(buildTransactionMappings());

        return dozerMapper;
    }

    @Bean("clientMappings")
    public BeanMappingBuilder buildClientMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(ClientEntity.class, Client.class)
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


    @Bean("employeeMappings")
    public BeanMappingBuilder buildEmployeeMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(EmployeeEntity.class, Employee.class)
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

    @Bean("bankAccountMappings")
    public BeanMappingBuilder buildBankAccountMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BankAccountEntity.class, BankAccount.class)
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

    @Bean("transactionMappings")
    public BeanMappingBuilder buildTransactionMappings() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(TransactionEntity.class, Transaction.class)
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
