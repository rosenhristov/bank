package com.rosenhristov.bank.controller;

import com.rosenhristov.bank.dto.TransactionDTO;
import com.rosenhristov.bank.entity.Transaction;
import com.rosenhristov.bank.exception.ErrorStub;
import com.rosenhristov.bank.service.TransactionService;
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
@RequestMapping("/transactions")
@AllArgsConstructor
@Api(value = "Operations related to financial transactions", tags = {"Transactions"})
@SwaggerDefinition(tags = {@Tag(name = "Transactions", description = "Operations related to financial transactions")})
public class TransactionController {

    private TransactionService service;

    @ApiOperation(value="Retrieve all financial transactions", notes = "Used to fetch all financial transactions from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<List<TransactionDTO>> getAll() {
        log.info("GETting all transactions");
        return ResponseEntity.of(
                Optional.of(service.getAll()));
    }

    @ApiOperation(value="Fetch a financial transaction by id",
                  notes = "Provide and id to lookup specific financial transaction from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @GetMapping(value = "/{transactionId}", produces = {"application/json"})
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        log.info("GETting transaction with id = {}", transactionId);
        return ResponseEntity.of(service.getTransactionById(transactionId));
    }

    @ApiOperation(
            value="Add a new financial transaction", notes = "Used to insert new financial transaction in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 201, message = "Created", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PostMapping(value = "/", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<TransactionDTO> addTransaction(@Valid @RequestBody TransactionDTO newTransaction) {
        log.info("INSERTing transaction");
        return ResponseEntity.of(
                Optional.ofNullable(service.save(
                        service.getMapper().toEntity(newTransaction))));
    }

    @ApiOperation(
            value="Modify a financial transaction",
            notes = "Used to replace an old financial transaction with a new one with a certain id in the database ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 201, message = "Created", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @PutMapping(value = "/{transactionId}", consumes = {"application/json"}, produces = {"application/json"})
    public  ResponseEntity<TransactionDTO> updateTransaction(@Valid @RequestBody TransactionDTO newTransaction,
                                                             @PathVariable Long transactionId) {
        log.info("UPDATE-ing transaction with id = {}", transactionId);
        return ResponseEntity.of(
                service.getTransactionById(transactionId)
                       .map(transaction -> {
                           transaction.setAmount(newTransaction.getAmount());
                           transaction.setSender(newTransaction.getSender());
                           transaction.setReceiver(newTransaction.getReceiver());
                           return service.save(
                                   service.getMapper().toEntity(transaction));
                       }));
    }

    @ApiOperation(value="Delete a financial transaction with indicated id",
                  notes = "Used to delete a financial transaction by id from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Transaction.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorStub.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorStub.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorStub.class),
            @ApiResponse(code = 500, message = "Server failure", response = ErrorStub.class)
    })
    @DeleteMapping(value = "/{transactionId}", produces = {"application/json"})
    public ResponseEntity<TransactionDTO> deleteTransaction(@PathVariable Long transactionId) {
        log.info("DELETE-ing transaction id = {}", transactionId);
        return ResponseEntity.of(
                service.deleteTransaction(transactionId));
    }
}
