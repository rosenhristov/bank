package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ClientMapper extends BaseMapper {

    @Autowired
    public ClientMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public ClientDTO toDto(Client entity) {
        log.info("Mapping Client entity to DTO");
        return mapper.map(entity, ClientDTO.class);
    }

    public Client toEntity(ClientDTO dto) {
        log.info("Mapping Client DTO to entity");
        return mapper.map(dto, Client.class);
    }

    public List<ClientDTO> toDtos(List<Client> entities) {
        log.info("Mapping Client entities to DTOs");
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Client> toEntities(List<ClientDTO> dtos) {
        log.info("Mapping Client DTOs to entities");
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public EmployeeDTO toEmployeeDto(Employee entity) {
        log.info("Mapping Employee entity to DTO");
        return mapper.map(entity, EmployeeDTO.class);
    }

    public Employee toEmployeeEntity(EmployeeDTO dto) {
        log.info("Mapping Employee DTO to entity");
        return mapper.map(dto, Employee.class);
    }

    public List<EmployeeDTO> toEmployeeDtos(List<Employee> entities) {
        log.info("Mapping Employee entities to DTOs");
        return entities.stream()
                .map(entity -> toEmployeeDto(entity))
                .collect(Collectors.toList());
    }

    public List<Employee> toEmployeeEntities(List<EmployeeDTO> dtos) {
        log.info("Mapping Employee DTOs to entities");
        return dtos.stream()
                .map(dto -> toEmployeeEntity(dto))
                .collect(Collectors.toList());
    }
}
