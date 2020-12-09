package com.dataart.bank.repository;

import com.dataart.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    default Optional<Transaction> removeTransactionById(Long id) {
        Optional<Transaction> transaction = findById(id);
        if (transaction.isPresent()) {
            delete(transaction.get());
            return transaction;
        }
        return Optional.empty();
    }
}
