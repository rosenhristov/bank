package com.rosenhristov.bank.mapper;

import com.rosenhristov.bank.pojo.BankAccount;
import com.rosenhristov.bank.pojo.Client;
import com.rosenhristov.bank.pojo.Employee;
import com.rosenhristov.bank.entity.BankAccountEntity;
import com.rosenhristov.bank.entity.ClientEntity;
import com.rosenhristov.bank.entity.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BankAccountMapper extends BaseMapper {

    @Autowired
    public BankAccountMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public BankAccount toDto(BankAccountEntity account) {
        log.info("Mapping BankAccount entity to DTO");
        return mapper.map(account, BankAccount.class);
    }

    public BankAccountEntity toEntity(BankAccount dto) {
        log.info("Mapping BankAccount DTO to entity");
        return mapper.map(dto, BankAccountEntity.class);
    }

    public List<BankAccount> toDtos(List<BankAccountEntity> entities) {
        log.info("Mapping BankAccount entities to DTOs");
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());

    }

    public List<BankAccountEntity> toEntities(List<BankAccount> dtos) {
        log.info("Mapping BankAccount DTOs to entities");
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public Employee toEmployeeDto(EmployeeEntity entity) {
        log.info("Mapping Employee entity to DTO");
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, Employee.class);
    }

    public EmployeeEntity toEmployeeEntity(Employee dto) {
        log.info("Mapping Employee DTO to entity");
        if (dto == null) {
            return null;
        }
        return mapper.map(dto, EmployeeEntity.class);
    }

    public List<Employee> toEmployeeDtos(List<EmployeeEntity> entities) {
        log.info("Mapping Employee entities to DTOs");
        return entities.stream()
                .map(entity -> toEmployeeDto(entity))
                .collect(Collectors.toList());
    }

    public List<EmployeeEntity> toEmployeeEntities(List<Employee> dtos) {
        log.info("Mapping Employee DTOs to entities");
        return dtos.stream()
                .map(dto -> toEmployeeEntity(dto))
                .collect(Collectors.toList());
    }


    public Client toClientDto(ClientEntity entity) {
        log.info("Mapping Client entity to DTO");
        return mapper.map(entity, Client.class);
    }

    public ClientEntity toClientEntity(Client dto) {
        log.info("Mapping Client DTO to entity");
        return mapper.map(dto, ClientEntity.class);
    }

    public List<Client> toClientDtos(List<ClientEntity> entities) {
        log.info("Mapping Client entities to DTOs");
        return entities.stream()
                .map(entity -> toClientDto(entity))
                .collect(Collectors.toList());
    }

    public List<ClientEntity> toClientEntities(List<Client> dtos) {
        log.info("Mapping Client DTOs to entities");
        return dtos.stream()
                   .map(dto -> toClientEntity(dto))
                   .collect(Collectors.toList());
    }
}
