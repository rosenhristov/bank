package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.dto.BankAccountDTO;
import com.rosenhristov.bank.dto.TransactionDTO;
import com.rosenhristov.bank.entity.BankAccount;
import com.rosenhristov.bank.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TransactionMapper extends BaseMapper {

    @Autowired
    public TransactionMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public TransactionDTO toDto(Transaction entity) {
        log.info("Mapping Transaction entity to DTO");
        return mapper.map(entity, TransactionDTO.class);
    }

    public Transaction toEntity(TransactionDTO dto) {
        return mapper.map(dto, Transaction.class);
    }

    public List<TransactionDTO> toDtos(List<Transaction> entities) {
        log.info("Mapping Transaction entities to DTOs");
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Transaction> toEntities(List<TransactionDTO> dtos) {
        log.info("Mapping Transaction DTO to entity");
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public BankAccountDTO toAccountDto(BankAccount account) {
        log.info("Mapping BankAccount entity to DTO");
        return mapper.map(account, BankAccountDTO.class);
    }

    public BankAccount toAccountEntity(BankAccountDTO dto) {
        log.info("Mapping BankAccount DTO to entity");
        return mapper.map(dto, BankAccount.class);
    }

    public List<BankAccountDTO> toAccountDtos(List<BankAccount> entities) {
        log.info("Mapping BankAccount entities to DTOs");
        return entities.stream()
                .map(entity -> toAccountDto(entity))
                .collect(Collectors.toList());

    }

    public List<BankAccount> toAccountEntities(List<BankAccountDTO> dtos) {
        log.info("Mapping BankAccount DTOs to entities");
        return dtos.stream()
                   .map(dto -> toAccountEntity(dto))
                   .collect(Collectors.toList());
    }
}
