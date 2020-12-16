package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EmployeeMapper extends BaseMapper {

    private BeanMappingBuilder builder = new BeanMappingBuilder() {
        @Override
        protected void configure() {
            mapping(Employee.class, EmployeeDTO.class)
                    .fields("id", "id")
                    .fields("name", "name")
                    .fields("midName", "midName")
                    .fields("surname", "surname")
                    .exclude("password")
                    .fields("position", "position")
                    .fields("salary", "salary")
                    .fields("phone", "phone")
                    .fields("email", "email")
                    .fields("address", "address")
                    .fields("dateHired", "dateHired")
                    .fields("startOfExperience", "startOfExperience")
                    .fields("clients", "clients")
                    .fields("accounts", "accounts")
                    .fields("dateCreated", "dateCreated")
                    .fields("dateUpdated", "dateUpdated");
        }
    };

    @Autowired
    public EmployeeMapper(DozerBeanMapper mapper) {
        super(mapper);
        mapper.addMapping(builder);
    }

    public EmployeeDTO toDto(Employee entity) {
        log.info("Mapping Employee entity to DTO");
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, EmployeeDTO.class);
    }

    public Employee toEntity(EmployeeDTO dto) {
        log.info("Mapping Employee DTO to entity");
        if (dto == null) {
            return null;
        }
        return mapper.map(dto, Employee.class);
    }

    public List<EmployeeDTO> toDtos(List<Employee> entities) {
        log.info("Mapping Employee entities to DTOs");
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Employee> toEntities(List<EmployeeDTO> dtos) {
        log.info("Mapping Employee DTOs to entities");
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }


    public ClientDTO toClientDto(Client entity) {
        log.info("Mapping Client entity to DTO");
        return mapper.map(entity, ClientDTO.class);
    }

    public Client toClientEntity(ClientDTO dto) {
        log.info("Mapping Client DTO to entity");
        return mapper.map(dto, Client.class);
    }

    public List<ClientDTO> toClientDtos(List<Client> entities) {
        log.info("Mapping Client entities to DTOs");
        return entities.stream()
                .map(entity -> toClientDto(entity))
                .collect(Collectors.toList());
    }

    public List<Client> toClientEntities(List<ClientDTO> dtos) {
        log.info("Mapping Client DTOs to entities");
        return dtos.stream()
                .map(dto -> toClientEntity(dto))
                .collect(Collectors.toList());
    }

}
