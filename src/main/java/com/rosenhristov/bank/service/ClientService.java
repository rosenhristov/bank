package com.rosenhristov.bank.service;

import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.pojo.Client;
import com.rosenhristov.bank.pojo.Employee;
import com.rosenhristov.bank.entity.BankAccountEntity;
import com.rosenhristov.bank.entity.ClientEntity;
import com.rosenhristov.bank.entity.EmployeeEntity;
import com.rosenhristov.bank.mapper.BankAccountMapper;
import com.rosenhristov.bank.mapper.ClientMapper;
import com.rosenhristov.bank.mapper.EmployeeMapper;
import com.rosenhristov.bank.repository.BankAccountRepository;
import com.rosenhristov.bank.repository.ClientRepository;
import com.rosenhristov.bank.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ClientService {

    private ClientMapper mapper;
    private EmployeeMapper employeeMapper;
    private BankAccountMapper bankAccountMapper;
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private BankAccountRepository bankAccountRepository;

    public Optional<Client> getClientById(Long id) {
        log.info("Retrieving client with id = {} from database", id);
        Optional<ClientEntity> clientOpt =  clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            return Optional.empty();
        }
        ClientEntity clientEntity = clientOpt.get();
        Client client = mapper.toDto(clientOpt.get());
        client.setAccountManager(loadAccountManager(clientEntity.getAccountManager()));
        client.setBankAccounts(loadBankAccounts(clientEntity.getBankAccountEntities()));
        return Optional.of(client);
    }

    public List<Client> getAll() {
        log.info("Retrieving all clients from database.");
        return mapper.toDtos((List<ClientEntity>) clientRepository.findAll());
    }

    public Client save(ClientEntity clientEntity) {
        log.info("Saving client {} {} to database",
                clientEntity.getName(),
                clientEntity.getSurname());
        return mapper.toDto(clientRepository.save(clientEntity));
    }

    public List<Client> saveAll(List<ClientEntity> clientEntity) {
        log.info("Saving all clients to database.");
        return mapper.toDtos((List<ClientEntity>) clientRepository.saveAll(clientEntity));
    }

    public Optional<Client> deleteClient(Long id) {
        log.info("Deleting client with id = {} from database", id);
        return clientRepository.removeClientById(id).map(entity -> mapper.toDto(entity));
    }

    public ClientMapper getMapper() {
        return mapper;
    }

    private Employee loadAccountManager(EmployeeEntity employeeEntity) {
        return employeeMapper.toDto(
                employeeRepository.findById(employeeEntity.getId()).get());
    }

    private List<BankAccount> loadBankAccounts(List<BankAccountEntity> bankAccountEntities) {
        return bankAccountMapper.toDtos( (List<BankAccountEntity>)
                bankAccountRepository.findAllById(
                        bankAccountEntities.stream()
                                    .map(account -> account.getId())
                                    .collect(Collectors.toList())));
    }
}
