package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.entity.BankAccountEntity;
import com.rosenhristov.bank.exception.BankException;
import com.rosenhristov.bank.exception.ErrorResponse;
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
            @ApiResponse(code = 200, message = "Success", response = BankAccountEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<BankAccount>> getAll() {
        log.info("GETting all bank accounts");
        return ResponseEntity.of(Optional.of(service.getAll()));
    }


    @ApiOperation(value="Fetch a bank account by id", notes = "Provide an id to lookup specific bank account from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccountEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{accountId}", produces = {"application/json"})
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long accountId) {
        log.info("GETting BankAccount with id {}", accountId);
        Optional<BankAccount> result = service.getBankAccountById(accountId);
        if (result.isEmpty()) {
            throw new BankException("Could not find bank account with id: " + accountId);
        }
        return ResponseEntity.of(result);
    }


    @ApiOperation(value="Add a new bank account", notes = "Used to insert new bank account in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccountEntity.class),
            @ApiResponse(code = 201, message = "Created", response = BankAccountEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<BankAccount> addBankAccount(@Valid @RequestBody BankAccount newAccount) {
        log.info("INSERTing bank account {}", newAccount.getAccountNumber());
        Optional<BankAccount> result = Optional.ofNullable(
                service.save(
                        service.getMapper().toEntity(newAccount)));
        if (result.isEmpty()) {
            throw new BankException("Could not save bank account with accountNumber: " + newAccount.getAccountNumber());
        }
        return ResponseEntity.of(result);
    }


    @ApiOperation(value="Update a bank account", notes = "Used to update a bank account with certain id in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccountEntity.class),
            @ApiResponse(code = 201, message = "Created", response = BankAccountEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PutMapping(value = "/{accountId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<BankAccount> updateBankAccount(@Valid @RequestBody BankAccount newAccount,
                                                         @PathVariable Long accountId) {
        log.info("UPDATE-ing bank account with id: {}", accountId);
        Optional<BankAccount> result = service.getBankAccountById(accountId);
        if (result.isEmpty()) {
            throw new BankException("Could not find bank account with id: " + accountId);
        }
        return ResponseEntity.of(
                result.map(account -> {
                    account.setAccountNumber(newAccount.getAccountNumber());
                    account.setIban(newAccount.getIban());
                    account.setType(newAccount.getType());
                    account.setCurrency(newAccount.getCurrency());
                    account.setBalance(newAccount.getBalance());
                    account.setClient(newAccount.getClient());
                    account.setAccountManager(newAccount.getAccountManager());
                    Optional<BankAccount> dto = Optional.ofNullable(
                            service.save(
                                    service.getMapper().toEntity(account)));
                    if (dto.isEmpty()) {
                        throw new BankException("Could not update bank account with id: " + accountId);
                    }
                    return dto.get();
                }));
    }



    @ApiOperation(value="Delete a bank account with indicated id",
                  notes = "Used to delete a bank account by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = BankAccountEntity.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @DeleteMapping(value = "/{accountId}", produces = {"application/json"})
    public ResponseEntity<BankAccount> deleteBankAccount(@PathVariable Long accountId) {
        log.info("DELETE-ing bank account {}", accountId);
        Optional<BankAccount> result = service.deleteBankAccount(accountId);
        if (result.isEmpty()) {
            throw new BankException("Could not delete bank account with id: " + accountId);
        }
        return ResponseEntity.of(result);
    }
}
