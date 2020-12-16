package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.entity.Client;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ClientMapperTest {

    @MockBean
    private static DozerBeanMapper dozer;

    @MockBean
    private static ClientMapper clientMapper;

    @BeforeAll
    public static void setup() {
        dozer = new DozerBeanMapper();
        clientMapper = new ClientMapper(dozer);
    }

    private Client initClient() {
       return new Client((long) 50, "Ross", "F.", "Morris", "P@ss0rd12345678",
                "0889679972", "ross@domain.com", "10 'Asen Bosev' Str., 1000 Sofia, Bulgaria",
                (long) 1131011, Date.valueOf("2020-07-01"), Date.valueOf("2030-07-01"), null,
                null, (long) 1324657980, (long)1176453120);
    }

    private ClientDTO initClientDto() {
        return new ClientDTO((long) 50, "Ross", "F.", "Morris",
                "0889679972", "ross@domain.com", "10 'Asen Bosev' Str., 1000 Sofia, Bulgaria",
                (long) 1131011, Date.valueOf("2020-07-01"), Date.valueOf("2030-07-01"), null,
                null, (long) 1324657980, (long)1176453120);
    }


    @Test
    public void toDtoTest() {
        Client client = initClient();
        ClientDTO dto = clientMapper.toDto(client);
        assertEquals( "id", dto.getId(), client.getId());
        assertEquals("name", dto.getName(), client.getName());
        assertEquals("midName", dto.getMidName(), client.getMidName());
        assertEquals("surname", dto.getSurname(), client.getSurname());
        assertEquals("phone", dto.getPhone(), client.getPhone());
        assertEquals("email", dto.getEmail(), client.getEmail());
        assertEquals("address", dto.getAddress(), client.getAddress());
        assertEquals("idCardNumber", dto.getIdCardNumber(), client.getIdCardNumber());
        assertEquals("idCardIssued", dto.getIdCardIssueDate(), client.getIdCardIssueDate());
        assertEquals("idCardExpiration", dto.getIdCardExpirationDate(), client.getIdCardExpirationDate());
        assertEquals("bankAccounts", dto.getBankAccounts(), client.getBankAccounts());
        assertEquals("accountManager", dto.getAccountManager(), client.getAccountManager());
        assertEquals("debitCardNumber", dto.getDebitCardNumber(), client.getDebitCardNumber());
        assertEquals("creditCardNumber", dto.getCreditCardNumber(), client.getCreditCardNumber());

    }

}
