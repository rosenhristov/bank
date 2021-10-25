package com.rosenhristov.bank.mapper;

import com.rosenhristov.bank.entity.TransactionEntity;
import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.pojo.Transaction;
import com.rosenhristov.bank.entity.BankAccountEntity;
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

    public Transaction toDto(TransactionEntity entity) {
        log.info("Mapping Transaction entity to DTO");
        return mapper.map(entity, Transaction.class);
    }

    public TransactionEntity toEntity(Transaction dto) {
        return mapper.map(dto, TransactionEntity.class);
    }

    public List<Transaction> toDtos(List<TransactionEntity> entities) {
        log.info("Mapping Transaction entities to DTOs");
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<TransactionEntity> toEntities(List<Transaction> dtos) {
        log.info("Mapping Transaction DTO to entity");
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public BankAccount toAccountDto(BankAccountEntity account) {
        log.info("Mapping BankAccount entity to DTO");
        return mapper.map(account, BankAccount.class);
    }

    public BankAccountEntity toAccountEntity(BankAccount dto) {
        log.info("Mapping BankAccount DTO to entity");
        return mapper.map(dto, BankAccountEntity.class);
    }

    public List<BankAccount> toAccountDtos(List<BankAccountEntity> entities) {
        log.info("Mapping BankAccount entities to DTOs");
        return entities.stream()
                .map(entity -> toAccountDto(entity))
                .collect(Collectors.toList());

    }

    public List<BankAccountEntity> toAccountEntities(List<BankAccount> dtos) {
        log.info("Mapping BankAccount DTOs to entities");
        return dtos.stream()
                   .map(dto -> toAccountEntity(dto))
                   .collect(Collectors.toList());
    }
}
