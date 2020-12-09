package com.dataart.bank.mapper;

import com.dataart.bank.dto.BankAccountDTO;
import com.dataart.bank.dto.TransactionDTO;
import com.dataart.bank.entity.BankAccount;
import com.dataart.bank.entity.Transaction;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper extends BaseMapper {

    @Autowired
    public TransactionMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public TransactionDTO toDto(Transaction entity) {
        return mapper.map(entity, TransactionDTO.class);
    }

    public Transaction toEntity(TransactionDTO dto) {
        return mapper.map(dto, Transaction.class);
    }

    public List<TransactionDTO> toDtos(List<Transaction> entities) {
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Transaction> toEntities(List<TransactionDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public BankAccountDTO toAccountDto(BankAccount account) {
        return mapper.map(account, BankAccountDTO.class);
    }

    public BankAccount toAccountEntity(BankAccountDTO dto) {
        return mapper.map(dto, BankAccount.class);
    }

    public List<BankAccountDTO> toAccountDtos(List<BankAccount> entities) {
        return entities.stream()
                .map(entity -> toAccountDto(entity))
                .collect(Collectors.toList());

    }

    public List<BankAccount> toAccountEntities(List<BankAccountDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toAccountEntity(dto))
                   .collect(Collectors.toList());
    }
}
