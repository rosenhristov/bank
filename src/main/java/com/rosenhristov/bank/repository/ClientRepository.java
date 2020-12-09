package com.rosenhristov.bank.repository;

import com.rosenhristov.bank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    default Optional<Client> removeClientById(Long id) {
        Optional<Client> client = findById(id);
        if (client.isPresent()) {
            delete(client.get());
            return client;
        }
        return Optional.empty();
    }
}
