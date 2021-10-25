package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.entity.ClientEntity;
import com.rosenhristov.bank.pojo.Client;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ClientEntityMapperTest {

    @MockBean
    private static DozerBeanMapper dozer;

    @MockBean
    private static ClientMapper clientMapper;

    @MockBean
    private static EmployeeMapper employeeMapper;

    @MockBean
    private static BankAccountMapper bankAccountMapper;

    @MockBean
    private static TransactionMapper transactionMapper;

    @BeforeAll
    public static void setup() {
        dozer = new DozerBeanMapper();
        clientMapper = new ClientMapper(dozer);
        employeeMapper = new EmployeeMapper(dozer);
        bankAccountMapper = new BankAccountMapper(dozer);
        transactionMapper = new TransactionMapper(dozer);
    }

    private ClientEntity buildClientEntity() {
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

    private Client buildClient() {
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


    @Test
    public void clientEntityToClientDtoTest() {
        ClientEntity clientEntity = buildClientEntity();
        Client dto = clientMapper.toDto(clientEntity);
        assertEquals( "id", dto.getId(), clientEntity.getId());
        assertEquals("name", dto.getName(), clientEntity.getName());
        assertEquals("midName", dto.getMidName(), clientEntity.getMidName());
        assertEquals("surname", dto.getSurname(), clientEntity.getSurname());
        assertEquals("phone", dto.getPhone(), clientEntity.getPhone());
        assertEquals("email", dto.getEmail(), clientEntity.getEmail());
        assertEquals("address", dto.getAddress(), clientEntity.getAddress());
        assertEquals("idCardNumber", dto.getIdCardNumber(), clientEntity.getIdCardNumber());
        assertEquals("idCardIssued", dto.getIdCardIssueDate(), clientEntity.getIdCardIssueDate());
        assertEquals("idCardExpiration", dto.getIdCardExpirationDate(), clientEntity.getIdCardExpirationDate());
        assertEquals("bankAccounts", dto.getBankAccounts(), clientEntity.getBankAccountEntities());
        assertEquals("accountManager", dto.getAccountManager(), clientEntity.getAccountManager());
        assertEquals("debitCardNumber", dto.getDebitCardNumber(), clientEntity.getDebitCardNumber());
        assertEquals("creditCardNumber", dto.getCreditCardNumber(), clientEntity.getCreditCardNumber());
    }

    @Test
    public void clientDtoToClientEntityTest() {
        Client clientDto = buildClient();
        ClientEntity clientEntity = clientMapper.toEntity(clientDto);
        assertEquals( "id", clientEntity.getId(), clientDto.getId());
        assertEquals("name", clientEntity.getName(), clientDto.getName());
        assertEquals("midName", clientEntity.getMidName(), clientDto.getMidName());
        assertEquals("surname", clientEntity.getSurname(), clientDto.getSurname());
        assertEquals("phone", clientEntity.getPhone(), clientDto.getPhone());
        assertEquals("email", clientEntity.getEmail(), clientDto.getEmail());
        assertEquals("address", clientEntity.getAddress(), clientDto.getAddress());
        assertEquals("idCardNumber", clientEntity.getIdCardNumber(), clientDto.getIdCardNumber());
        assertEquals("idCardIssued", clientEntity.getIdCardIssueDate(), clientDto.getIdCardIssueDate());
        assertEquals("idCardExpiration", clientEntity.getIdCardExpirationDate(), clientDto.getIdCardExpirationDate());
        assertEquals("bankAccounts", clientEntity.getBankAccountEntities(), clientDto.getBankAccounts());
        assertEquals("accountManager", clientEntity.getAccountManager(), clientDto.getAccountManager());
        assertEquals("debitCardNumber", clientEntity.getDebitCardNumber(), clientDto.getDebitCardNumber());
        assertEquals("creditCardNumber", clientEntity.getCreditCardNumber(), clientDto.getCreditCardNumber());
    }
}
