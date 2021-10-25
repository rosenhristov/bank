package com.rosenhristov.bank.service;

import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.entity.BankAccountEntity;
import com.rosenhristov.bank.exception.mapper.BankAccountMapper;
import com.rosenhristov.bank.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper mapper;

    public Optional<BankAccount> getBankAccountById(Long id) {
        log.info("Retrieving bank account with id = {} from database", id);
        return bankAccountRepository
                .findById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public List<BankAccount> getAll() {
        log.info("Retrieving all bank accounts from database.");
        return mapper.toDtos((List<BankAccountEntity>) bankAccountRepository.findAll());
    }

    public BankAccount save(BankAccountEntity bankAccountEntity) {
        log.info("Saving bank account {} to database", bankAccountEntity.getAccountNumber());
        return mapper.toDto(bankAccountRepository.save(bankAccountEntity));
    }

    public List<BankAccount> saveAll(List<BankAccountEntity> bankAccountEntities) {
        log.info("Saving all bank accounts to database.");
        return mapper.toDtos((List<BankAccountEntity>) bankAccountRepository.saveAll(bankAccountEntities));
    }

    public Optional<BankAccount> deleteBankAccount(Long id) {
        log.info("Deleting bank account with id = {} from database", id);
        return bankAccountRepository
                .removeAccountById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public BankAccountMapper getMapper() {
        return mapper;
    }
}
