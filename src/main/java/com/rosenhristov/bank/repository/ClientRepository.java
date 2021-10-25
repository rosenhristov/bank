package com.rosenhristov.bank.repository;

import com.rosenhristov.bank.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    default Optional<ClientEntity> removeClientById(Long id) {
        Optional<ClientEntity> client = findById(id);
        if (client.isPresent()) {
            delete(client.get());
            return client;
        }
        return Optional.empty();
    }
}
