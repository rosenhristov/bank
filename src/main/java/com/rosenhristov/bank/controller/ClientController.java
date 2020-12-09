package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.exception.ErrorStub;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.service.ClientService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/clients")
@Api(value = "Operations related to clients", tags = {"Clients"})
@SwaggerDefinition(tags = {@Tag(name = "Clients", description = "Operations related to clients") })
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @ApiOperation(
            value="Retrieve all clients",
            notes = "Used to fetch all clients from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 201, message = "Created", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<ClientDTO>> getAll() {
        return ResponseEntity.of(
                Optional.of(service.getAll()));
    }



    @ApiOperation(
            value="Fetch a client by id",
            notes = "Provide and id to lookup specific client from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/{clientId}", produces = {"application/json"})
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long clientId) {
        return ResponseEntity.of(
                service.getClientById(clientId));
    }



    @ApiOperation(
            value="Add a new client",
            notes = "Used to insert new client in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 201, message = "Created", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ClientDTO> addCustomer(@Valid @RequestBody ClientDTO newClient) {
        return ResponseEntity.of(
                Optional.ofNullable(service.save(
                        service.getMapper().toEntity(newClient))));
    }


    @ApiOperation(
            value="Modify a client",
            notes = "Used to replace an old client with a new one with a certain id in the database ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 201, message = "Created", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PutMapping(value = "/{clientId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ClientDTO> updateClient(@Valid @RequestBody ClientDTO newClient, @PathVariable Long clientId) {
        Optional<ClientDTO> opt = service.getClientById(clientId);
            return ResponseEntity.of(opt
                            .map(client -> {
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
                                return service.save(
                                        service.getMapper().toEntity(client));
                            }));
        }



    @ApiOperation(value="Delete a client with indicated id",
            notes = "Used to delete a client by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Client.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @DeleteMapping(value = "/{clientId}", produces = {"application/json"})
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Long clientId) {
        return ResponseEntity.of(
                service.deleteClient(clientId));
    }
}
