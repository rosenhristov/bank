package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.TransactionDTO;
import com.rosenhristov.bank.entity.Transaction;
import com.rosenhristov.bank.exception.BankException;
import com.rosenhristov.bank.exception.ErrorResponse;
import com.rosenhristov.bank.service.TransactionService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@NoArgsConstructor
@Api(value = "Operations related to financial transactions", tags = {"Transactions"})
@SwaggerDefinition(tags = {@Tag(name = "Transactions", description = "Operations related to financial transactions")})
public class TransactionController {

    private TransactionService service;

    @ApiOperation(value="Retrieve all financial transactions", notes = "Used to fetch all financial transactions from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public CollectionModel<EntityModel<TransactionDTO>> getAll() {
        log.info("GETting all transactions");
        return (CollectionModel<EntityModel<TransactionDTO>>)
                CollectionModel.of(
                        EntityModel.of(service.getAll()),
                        List.of(Link.of("http://localhost:8080/bank/transactions/transactionId/")));
    }

    @ApiOperation(value="Fetch a financial transaction by id",
                  notes = "Provide and id to lookup specific financial transaction from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @GetMapping(value = "/{transactionId}", produces = {"application/json"})
    public EntityModel<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        log.info("GETting transaction with id = {}", transactionId);
        Optional<TransactionDTO> result = service.getTransactionById(transactionId);
        if (result.isEmpty()) {
            throw new BankException("Could not find bank account with id: " + transactionId);
        }
        return EntityModel.of(
                result.get(),
                Link.of("http://localhost:8080/bank/transactions/"));
    }

    @ApiOperation(
            value="Add a new financial transaction", notes = "Used to insert new financial transaction in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 201, message = "Created", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public EntityModel<TransactionDTO> addTransaction(@Valid @RequestBody TransactionDTO newTransaction) {
        log.info("INSERTing transaction");
        Optional<TransactionDTO> result = Optional.ofNullable(
                service.save(
                        service.getMapper().toEntity(newTransaction)));
        if (result.isEmpty()) {
            throw new BankException(String.format("Could not save transaction between accounts %s and %s",
                    newTransaction.getSender(), newTransaction.getReceiver()));
        }
        return EntityModel.of(
                result.get(),
                Link.of("http://localhost:8080/bank/transactions/"));
    }

    @ApiOperation(
            value="Modify a financial transaction",
            notes = "Used to replace an old financial transaction with a new one with a certain id in the database ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 201, message = "Created", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @PutMapping(value = "/{transactionId}", consumes = {"application/json"}, produces = {"application/json"})
    public  EntityModel<TransactionDTO> updateTransaction(@Valid @RequestBody TransactionDTO newTransaction,
                                                             @PathVariable Long transactionId) {
        log.info("UPDATE-ing transaction with id = {}", transactionId);
        Optional<TransactionDTO> result = service.getTransactionById(transactionId);
        if (result.isEmpty()) {
            throw new BankException("Could not find transaction with id: " + transactionId);
        }
        return EntityModel.of(
                result.map(
                        transaction -> {
                            transaction.setAmount(newTransaction.getAmount());
                            transaction.setSender(newTransaction.getSender());
                            transaction.setReceiver(newTransaction.getReceiver());
                            Optional<TransactionDTO> dto = Optional.ofNullable(
                                    service.save(
                                            service.getMapper().toEntity(transaction)));
                            if (dto.isEmpty()) {
                                throw new BankException("Could not update transaction with id: " + transactionId);
                            }
                            return dto.get();
                        }).get(),
                List.of(Link.of("http://localhost:8080/bank/transactions/")));
    }

    @ApiOperation(value="Delete a financial transaction with indicated id",
            notes = "Used to delete a financial transaction by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorResponse.class)
    })
    @DeleteMapping(value = "/{transactionId}", produces = {"application/json"})
    public EntityModel<TransactionDTO> deleteTransaction(@PathVariable Long transactionId) {
        log.info("DELETE-ing transaction id = {}", transactionId);
        Optional<TransactionDTO> result = service.deleteTransaction(transactionId);
        if (result.isEmpty()) {
            throw new BankException("Could not delete bank account with id: " + transactionId);
        }
        return EntityModel.of(
                result.get(),
                List.of(Link.of("http://localhost:8080/bank/transactions/")));
    }
}
