package com.rosenhristov.bank.repository;

import com.rosenhristov.bank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    default Optional<BankAccount> removeAccountById(Long id) {
        Optional<BankAccount> account = findById(id);
        if (account.isPresent()) {
            delete(account.get());
            return account;
        }
        return Optional.empty();
    }
}
