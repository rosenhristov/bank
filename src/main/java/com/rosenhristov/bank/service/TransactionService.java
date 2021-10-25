package com.rosenhristov.bank.service;

import com.rosenhristov.bank.entity.TransactionEntity;
import com.rosenhristov.bank.pojo.Transaction;
import com.rosenhristov.bank.exception.mapper.TransactionMapper;
import com.rosenhristov.bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    public Optional<Transaction> getTransactionById(Long id) {
        log.info("Retrieving Transaction with id = {} from database", id);
        return repository.findById(id).map(entity -> mapper.toDto(entity));
    }

    public List<Transaction> getAll() {
        log.info("Retrieving all the Transaction entities from database.");
        return mapper.toDtos((List<TransactionEntity>) repository.findAll());
    }

    public Transaction save(TransactionEntity transactionEntity) {
        log.info("Saving Transaction between accounts {} and {} to database",
                transactionEntity.getSender().getAccountNumber(),
                transactionEntity.getReceiver().getAccountNumber());
        return mapper.toDto(repository.save(transactionEntity));
    }

    public List<Transaction> saveAll(List<TransactionEntity> transactionEntities) {
        log.info("Saving all transactions to database.");
        return mapper.toDtos((List<TransactionEntity>) repository.saveAll(transactionEntities));
    }

    public Optional<Transaction> deleteTransaction(Long id) {
        log.info("Deleting transaction with id = {} from database", id);
        return repository.removeTransactionById(id).map(entity -> mapper.toDto(entity));
    }

    public TransactionMapper getMapper() {
        return mapper;
    }
}
