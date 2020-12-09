package com.rosenhristov.bank.mapper;

import com.rosenhristov.bank.dto.AddressDTO;
import com.rosenhristov.bank.dto.ClientDTO;
import com.rosenhristov.bank.dto.EmployeeDTO;
import com.rosenhristov.bank.entity.Address;
import com.rosenhristov.bank.entity.Client;
import com.rosenhristov.bank.entity.Employee;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper extends BaseMapper {

    @Autowired
    public EmployeeMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public EmployeeDTO toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, EmployeeDTO.class);
    }

    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }
        return mapper.map(dto, Employee.class);
    }

    public List<EmployeeDTO> toDtos(List<Employee> entities) {
        return entities.stream()
                       .map(entity -> toDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Employee> toEntities(List<EmployeeDTO> dtos) {
        return dtos.stream()
                   .map(dto -> toEntity(dto))
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
