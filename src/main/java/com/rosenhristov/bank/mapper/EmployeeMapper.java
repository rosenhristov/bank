package com.rosenhristov.bank.mapper;

import com.rosenhristov.bank.pojo.Client;
import com.rosenhristov.bank.pojo.Employee;
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
public class EmployeeMapper extends BaseMapper {

    @Autowired
    public EmployeeMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public Employee toDto(EmployeeEntity entity) {
        log.info("Mapping Employee entity to DTO");
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, Employee.class);
    }

    public EmployeeEntity toEntity(Employee dto) {
        log.info("Mapping Employee DTO to entity");
        if (dto == null) {
            return null;
        }
        return mapper.map(dto, EmployeeEntity.class);
    }

    public List<Employee> toDtos(List<EmployeeEntity> entities) {
        log.info("Mapping Employee entities to DTOs");
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<EmployeeEntity> toEntities(List<Employee> dtos) {
        log.info("Mapping Employee DTOs to entities");
        return dtos.stream()
                   .map(dto -> toEntity(dto))
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
