package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.pojo.Client;
import com.rosenhristov.bank.entity.ClientEntity;
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

    @BeforeAll
    public static void setup() {
        dozer = new DozerBeanMapper();
        clientMapper = new ClientMapper(dozer);
    }

    private ClientEntity initClient() {
       return new ClientEntity((long) 50, "Ross", "F.", "Morris", "P@ss0rd12345678",
                "0889679972", "ross@domain.com", "10 'Asen Bosev' Str., 1000 Sofia, Bulgaria",
                (long) 1131011, Date.valueOf("2020-07-01"), Date.valueOf("2030-07-01"), null,
                null, (long) 1324657980, (long)1176453120);
    }

    private Client initClientDto() {
        return new Client((long) 50, "Ross", "F.", "Morris",
                "0889679972", "ross@domain.com", "10 'Asen Bosev' Str., 1000 Sofia, Bulgaria",
                (long) 1131011, Date.valueOf("2020-07-01"), Date.valueOf("2030-07-01"), null,
                null, (long) 1324657980, (long)1176453120);
    }


    @Test
    public void toDtoTest() {
        ClientEntity clientEntity = initClient();
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

}
