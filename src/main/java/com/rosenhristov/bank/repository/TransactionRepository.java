package com.rosenhristov.bank.repository;

import com.rosenhristov.bank.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

    default Optional<TransactionEntity> removeTransactionById(Long id) {
        Optional<TransactionEntity> transaction = findById(id);
        if (transaction.isPresent()) {
            delete(transaction.get());
            return transaction;
        }
        return Optional.empty();
    }
}
