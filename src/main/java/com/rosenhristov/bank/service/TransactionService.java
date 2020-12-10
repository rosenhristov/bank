package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.TransactionDTO;
import com.rosenhristov.bank.entity.Transaction;
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

    public Optional<TransactionDTO> getTransactionById(Long id) {
        log.info("Retrieving Transaction with id = {} from database", id);
        return repository
                .findById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public List<TransactionDTO> getAll() {
        log.info("Retrieving all the Transaction entities from database.");
        return mapper.toDtos(repository.findAll());
    }

    public TransactionDTO save(Transaction transaction) {
        log.info("Saving Transaction between accounts {} and {} to database",
                transaction.getSender().getAccountNumber(),
                transaction.getReceiver().getAccountNumber());
        return mapper.toDto(repository.save(transaction));
    }

    public List<TransactionDTO> saveAll(List<Transaction> transactions) {
        log.info("Saving all transactions to database.");
        return mapper.toDtos(repository.saveAll(transactions));
    }

    public Optional<TransactionDTO> deleteTransaction(Long id) {
        log.info("Deleting transaction with id = {} from database", id);
        return repository.removeTransactionById(id).map(entity -> mapper.toDto(entity));
    }

    public TransactionMapper getMapper() {
        return mapper;
    }
}
