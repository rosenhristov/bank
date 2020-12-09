package com.dataart.bank.mapper;

import com.dataart.bank.dto.BankAccountDTO;
import com.dataart.bank.dto.ClientDTO;
import com.dataart.bank.dto.EmployeeDTO;
import com.dataart.bank.entity.BankAccount;
import com.dataart.bank.entity.Client;
import com.dataart.bank.entity.Employee;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankAccountMapper extends BaseMapper {

    @Autowired
    public BankAccountMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public BankAccountDTO toDto(BankAccount account) {
        return mapper.map(account, BankAccountDTO.class);
    }

    public BankAccount toEntity(BankAccountDTO dto) {
        return mapper.map(dto, BankAccount.class);
    }

    public List<BankAccountDTO> toDtos(List<BankAccount> entities) {
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());

    }

    public List<BankAccount> toEntities(List<BankAccountDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public EmployeeDTO toEmployeeDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, EmployeeDTO.class);
    }

    public Employee toEmployeeEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        return mapper.map(dto, Employee.class);
    }

    public List<EmployeeDTO> toEmployeeDtos(List<Employee> entities) {
        return entities.stream()
                .map(entity -> toEmployeeDto(entity))
                .collect(Collectors.toList());
    }

    public List<Employee> toEmployeeEntities(List<EmployeeDTO> dtos) {
        return dtos.stream()
                .map(dto -> toEmployeeEntity(dto))
                .collect(Collectors.toList());
    }


    public ClientDTO toClientDto(Client entity) {
        return mapper.map(entity, ClientDTO.class);
    }

    public Client toClientEntity(ClientDTO dto) {
        return mapper.map(dto, Client.class);
    }

    public List<ClientDTO> toClientDtos(List<Client> entities) {
        return entities.stream()
                .map(entity -> toClientDto(entity))
                .collect(Collectors.toList());
    }

    public List<Client> toClientEntities(List<ClientDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toClientEntity(dto))
                   .collect(Collectors.toList());
    }
}
