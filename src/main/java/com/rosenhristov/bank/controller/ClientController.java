package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.exception.BankException;
import com.rosenhristov.bank.exception.ErrorResponse;
import com.rosenhristov.bank.service.ClientService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/clients")
@AllArgsConstructor
@Api(value = "Operations related to clients", tags = {"Clients"})
@SwaggerDefinition(tags = {@Tag(name = "Clients", description = "Operations related to clients") })
public class ClientController {

    private final ClientService service;

    @ApiOperation(value = "Retrieve all clients", notes = "Used to fetch all clients from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 201, message = "Created", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<ClientDTO>> getAll() {
        log.info("GETting all clients");
        return ResponseEntity.of(
                Optional.of(service.getAll()));
    }



    @ApiOperation(value="Fetch a client by id", notes = "Provide and id to lookup specific client from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{clientId}", produces = {"application/json"})
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long clientId) {
        log.info("GETting client with id = {}", clientId);
        Optional<ClientDTO> result = service.getClientById(clientId);
        if (result.isEmpty()) {
            throw new BankException("Could not find client with id: " + clientId);
        }
        return ResponseEntity.of(result);
    }



    @ApiOperation(
            value="Add a new client",
            notes = "Used to insert new client in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 201, message = "Created", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ClientDTO> addCustomer(@Valid @RequestBody ClientDTO newClient) {
        Optional<ClientDTO> result = Optional.ofNullable(
                service.save(
                        service.getMapper().toEntity(newClient)));
        if (result.isEmpty()) {
            throw new BankException(String.format("Could not save client %s %s",
                    newClient.getName(), newClient.getSurname()));
        }
        return ResponseEntity.of(result);
    }


    @ApiOperation(value="Update a client", notes = "Used to update a client with a certain id in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 201, message = "Created", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PutMapping(value = "/{clientId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ClientDTO> updateClient(@Valid @RequestBody ClientDTO newClient,
                                                  @PathVariable Long clientId) {
        log.info("UPDATE-ing client with id = {}", clientId);
        Optional<ClientDTO> result = service.getClientById(clientId);
        if (result.isEmpty()) {
            throw new BankException("Could not find client with id = " + clientId);
        }
        return ResponseEntity.of(
                result.map(client -> {
                    client.setName(newClient.getName());
                    client.setMidName(newClient.getMidName());
                    client.setSurname(newClient.getSurname());
                    client.setPhone(newClient.getPhone());
                    client.setEmail(newClient.getEmail());
                    client.setAddress(newClient.getAddress());
                    client.setIdCardNumber(newClient.getIdCardNumber());
                    client.setIdCardIssueDate(newClient.getIdCardIssueDate());
                    client.setIdCardExpirationDate(newClient.getIdCardExpirationDate());
                    client.setBankAccounts(newClient.getBankAccounts());
                    client.setAccountManager(newClient.getAccountManager());
                    client.setDebitCardNumber(newClient.getDebitCardNumber());
                    client.setCreditCardNumber(newClient.getDebitCardNumber());
                    Optional<ClientDTO> dto = Optional.ofNullable(
                            service.save(
                                    service.getMapper().toEntity(client)));
                    if (dto.isEmpty()) {
                        throw new BankException("Could not update client with id: " + clientId);
                    }
                    return dto.get();
                }));
    }

    @ApiOperation(value="Delete a client with indicated id",
            notes = "Used to delete a client by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @DeleteMapping(value = "/{clientId}", produces = {"application/json"})
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Long clientId) {
        log.info("DELETE-ing bank account {}", clientId);
        Optional<ClientDTO> result = service.deleteClient(clientId);
        if (result.isEmpty()) {
            throw new BankException("Could not delete client with id = " + clientId);
        }
        return ResponseEntity.of(result);
    }
}
