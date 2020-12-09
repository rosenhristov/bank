package com.dataart.bank.mapper;

import com.dataart.bank.dto.AddressDTO;
import com.dataart.bank.dto.ClientDTO;
import com.dataart.bank.dto.EmployeeDTO;
import com.dataart.bank.entity.Address;
import com.dataart.bank.entity.Client;
import com.dataart.bank.entity.Employee;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper extends BaseMapper {

    @Autowired
    public ClientMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public ClientDTO toDto(Client entity) {
        return mapper.map(entity, ClientDTO.class);
    }

    public Client toEntity(ClientDTO dto) {
        return mapper.map(dto, Client.class);
    }

    public List<ClientDTO> toDtos(List<Client> entities) {
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Client> toEntities(List<ClientDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toEntity(dto))
                   .collect(Collectors.toList());
    }

    public EmployeeDTO toEmployeeDto(Employee entity) {
        return mapper.map(entity, EmployeeDTO.class);
    }

    public Employee toEmployeeEntity(EmployeeDTO dto) {
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

    public AddressDTO toAddressDto(Address entity) {
        return mapper.map(entity, AddressDTO.class);
    }

    public Address toAddressEntity(AddressDTO dto) {
        return mapper.map(dto, Address.class);
    }

    public List<AddressDTO> toAddressDtos(List<Address> entities) {
        return entities.stream()
                .map(entity -> toAddressDto(entity))
                .collect(Collectors.toList());
    }

    public List<Address> toAddressEntities(List<AddressDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toAddressEntity(dto))
                   .collect(Collectors.toList());
    }
}
