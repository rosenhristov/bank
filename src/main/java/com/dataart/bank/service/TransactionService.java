package com.dataart.bank.service;

import com.dataart.bank.dto.TransactionDTO;
import com.dataart.bank.mapper.TransactionMapper;
import com.dataart.bank.entity.Transaction;
import com.dataart.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Autowired
    public TransactionService(TransactionRepository repository, TransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<TransactionDTO> getTransactionById(Long id) {
        return repository.findById(id).map(entity -> mapper.toDto(entity));
    }

    public List<TransactionDTO> getAll() {
        return mapper.toDtos(repository.findAll());
    }

    public TransactionDTO save(Transaction employee) {
        return mapper.toDto(repository.save(employee));
    }

    public List<TransactionDTO> saveAll(List<Transaction> client) {
        return mapper.toDtos(repository.saveAll(client));
    }

    public Optional<TransactionDTO> deleteTransaction(Long id) {
        return repository.removeTransactionById(id).map(entity -> mapper.toDto(entity));
    }

    public TransactionMapper getMapper() {
        return mapper;
    }
}
