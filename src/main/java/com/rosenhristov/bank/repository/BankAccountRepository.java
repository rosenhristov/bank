package com.rosenhristov.bank.repository;

import com.rosenhristov.bank.entity.BankAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccountEntity, Long> {

    default Optional<BankAccountEntity> removeAccountById(Long id) {
        Optional<BankAccountEntity> account = findById(id);
        if (account.isPresent()) {
            delete(account.get());
            return account;
        }
        return Optional.empty();
    }
}
