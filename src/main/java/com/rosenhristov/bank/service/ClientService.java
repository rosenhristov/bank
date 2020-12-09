package com.rosenhristov.bank.service;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.mapper.ClientMapper;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public Optional<ClientDTO> getClientById(Long id) {
        return clientRepository
                .findById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public List<ClientDTO> getAll() {
        return new LinkedList<>(
                mapper.toDtos(
                        clientRepository.findAll()));
    }

    public ClientDTO save(Client employee) {
        return mapper.toDto(clientRepository.save(employee));
    }

    public List<ClientDTO> saveAll(List<Client> client) {
        return mapper.toDtos(
                clientRepository.saveAll(client));
    }

    public Optional<ClientDTO> deleteClient(Long id) {
        return clientRepository
                .removeClientById(id)
                .map(entity -> mapper.toDto(entity));
    }

    public ClientMapper getMapper() {
        return mapper;
    }
}
