package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.BankAccountDTO;
import com.rosenhristov.bank.entity.BankAccount;
import com.rosenhristov.bank.exception.ErrorStub;
import com.rosenhristov.bank.service.BankAccountService;
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
@RequestMapping("/accounts")
@AllArgsConstructor
@Api(value = "Operations related to bank accounts", tags = {"Bank Accounts"})
@SwaggerDefinition(tags = {@Tag(name = "Bank Accounts", description = "Operations related to bank accounts") })
public class BankAccountController {

    private final BankAccountService service;

    @ApiOperation(value="Retrieve all bank accounts", notes = "Used to fetch all bank accounts from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccount.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<BankAccountDTO>> getAll() {
        log.info("GETting all bank accounts");
        return ResponseEntity.of(Optional.of(service.getAll()));
    }


    @ApiOperation(value="Fetch a bank account by id", notes = "Provide an id to lookup specific bank account from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccount.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/{accountId}", produces = {"application/json"})
    public ResponseEntity<BankAccountDTO> getBankAccountById(@PathVariable Long accountId) {
        log.info("GETting BankAccount with id {}", accountId);
        return ResponseEntity.of(service.getBankAccountById(accountId));
    }


    @ApiOperation(value="Add a new bank account", notes = "Used to insert new bank account in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccount.class),
            @ApiResponse(code = 201, message = "Created", response = BankAccount.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<BankAccountDTO> addBankAccount(@Valid @RequestBody BankAccountDTO newAccount) {
        log.info("INSERTing bank account {}", newAccount.getAccountNumber());
        return ResponseEntity.of(
                Optional.ofNullable(service.save(
                        service.getMapper().toEntity(newAccount))));
    }


    @ApiOperation(value="Update a bank account", notes = "Used to update a bank account with certain id in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccount.class),
            @ApiResponse(code = 201, message = "Created", response = BankAccount.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PutMapping(value = "/{accountId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<BankAccountDTO> updateBankAccount(@Valid @RequestBody BankAccountDTO newAccount,
                                                            @PathVariable Long accountId) {
        log.info("UPDATE-ing bank account with id = {}", accountId);
        return ResponseEntity.of(
                service.getBankAccountById(accountId)
                        .map(account -> {
                            account.setAccountNumber(newAccount.getAccountNumber());
                            account.setIban(newAccount.getIban());
                            account.setType(newAccount.getType());
                            account.setCurrency(newAccount.getCurrency());
                            account.setBalance(newAccount.getBalance());
                            account.setClient(newAccount.getClient());
                            account.setAccountManager(newAccount.getAccountManager());
                            return service.save(
                                    service.getMapper().toEntity(account));
                        }));
    }



    @ApiOperation(value="Delete a bank account with indicated id",
                  notes = "Used to delete a bank account by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccount.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @DeleteMapping(value = "/{accountId}", produces = {"application/json"})
    public ResponseEntity<BankAccountDTO> deleteBankAccount(@PathVariable Long accountId) {
        log.info("DELETE-ing bank account {}", accountId);
        return ResponseEntity.of(
                service.deleteBankAccount(accountId));
    }
}
