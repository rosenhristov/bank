package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.exception.mapper.ClientMapper;
import com.rosenhristov.bank.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    public Optional<ClientDTO> getClientById(Long id) {
        log.info("Retrieving client with id = {} from database", id);
        return clientRepository
                .findById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public List<ClientDTO> getAll() {
        log.info("Retrieving all clients from database.");
        return new LinkedList<>(
                mapper.toDtos(
                        clientRepository.findAll()));
    }

    public ClientDTO save(Client client) {
        log.info("Saving client {} {} to database",
                client.getName(),
                client.getSurname());
        return mapper.toDto(clientRepository.save(client));
    }

    public List<ClientDTO> saveAll(List<Client> client) {
        log.info("Saving all clients to database.");
        return mapper.toDtos(
                clientRepository.saveAll(client));
    }

    public Optional<ClientDTO> deleteClient(Long id) {
        log.info("Deleting client with id = {} from database", id);
        return clientRepository
                .removeClientById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public ClientMapper getMapper() {
        return mapper;
    }
}
