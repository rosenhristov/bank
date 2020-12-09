package com.dataart.bank.service;

import com.dataart.bank.dto.BankAccountDTO;
import com.dataart.bank.mapper.BankAccountMapper;
import com.dataart.bank.entity.BankAccount;
import com.dataart.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper mapper;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, BankAccountMapper mapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.mapper = mapper;
    }

    public Optional<BankAccountDTO> getBankAccountById(Long id) {
        return bankAccountRepository
                .findById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public List<BankAccountDTO> getAll() {
        return mapper.toDtos(bankAccountRepository.findAll());
    }

    public BankAccountDTO save(BankAccount employee) {
        return mapper.toDto(bankAccountRepository.save(employee));
    }

    public List<BankAccountDTO> saveAll(List<BankAccount> client) {
        return mapper.toDtos(bankAccountRepository.saveAll(client));
    }

    public Optional<BankAccountDTO> deleteBankAccount(Long id) {
        return bankAccountRepository
                .removeAccountById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public BankAccountMapper getMapper() {
        return mapper;
    }
}
