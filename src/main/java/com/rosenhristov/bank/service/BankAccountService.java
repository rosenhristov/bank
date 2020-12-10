package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.BankAccountDTO;
import com.rosenhristov.bank.entity.BankAccount;
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

    public Optional<BankAccountDTO> getBankAccountById(Long id) {
        log.info("Retrieving bank account with id = {} from database", id);
        return bankAccountRepository
                .findById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public List<BankAccountDTO> getAll() {
        log.info("Retrieving all bank accounts from database.");
        return mapper.toDtos(bankAccountRepository.findAll());
    }

    public BankAccountDTO save(BankAccount bankAccount) {
        log.info("Saving bank account {} to database", bankAccount.getAccountNumber());
        return mapper.toDto(bankAccountRepository.save(bankAccount));
    }

    public List<BankAccountDTO> saveAll(List<BankAccount> bankAccounts) {
        log.info("Saving all bank accounts to database.");
        return mapper.toDtos(bankAccountRepository.saveAll(bankAccounts));
    }

    public Optional<BankAccountDTO> deleteBankAccount(Long id) {
        log.info("Deleting bank account with id = {} from database", id);
        return bankAccountRepository
                .removeAccountById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public BankAccountMapper getMapper() {
        return mapper;
    }
}
