package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.BankAccountDTO;
import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.BankAccount;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.entity.Employee;
import com.rosenhristov.bank.exception.mapper.BankAccountMapper;
import com.rosenhristov.bank.exception.mapper.ClientMapper;
import com.rosenhristov.bank.exception.mapper.EmployeeMapper;
import com.rosenhristov.bank.repository.BankAccountRepository;
import com.rosenhristov.bank.repository.ClientRepository;
import com.rosenhristov.bank.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public Optional<ClientDTO> getClientById(Long id) {
        log.info("Retrieving client with id = {} from database", id);
        Optional<Client> clientOpt =  clientRepository.findById(id);
        if (clientOpt.isEmpty()) {
            return Optional.empty();
        }
        Client client = clientOpt.get();
        ClientDTO dto = mapper.toDto(clientOpt.get());
        dto.setAccountManager(loadAccountManager(client.getAccountManager()));
        dto.setBankAccounts(loadBankAccounts(client.getBankAccounts()));
        return Optional.of(dto);
    }

    public List<ClientDTO> getAll() {
        log.info("Retrieving all clients from database.");
        return new LinkedList<>(
                mapper.toDtos(
                        clientRepository.findAll()));
    }

    public ClientDTO save(Client client) {
        log.info("Saving client {} {} to database",
                client.getName(),
                client.getSurname());
        return mapper.toDto(clientRepository.save(client));
    }

    public List<ClientDTO> saveAll(List<Client> client) {
        log.info("Saving all clients to database.");
        return mapper.toDtos(
                clientRepository.saveAll(client));
    }

    public Optional<ClientDTO> deleteClient(Long id) {
        log.info("Deleting client with id = {} from database", id);
        return clientRepository
                .removeClientById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public ClientMapper getMapper() {
        return mapper;
    }

    private EmployeeDTO loadAccountManager(Employee employee) {
        return employeeMapper.toDto(
                employeeRepository.findById(employee.getId()).get());
    }

    private List<BankAccountDTO> loadBankAccounts(List<BankAccount> bankAccounts) {
        return bankAccountMapper.toDtos(
                bankAccountRepository.findAllById(
                        bankAccounts.stream()
                                    .map(account -> account.getId())
                                    .collect(Collectors.toList())));
    }
}
