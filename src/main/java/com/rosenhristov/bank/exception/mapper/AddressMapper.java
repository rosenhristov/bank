package com.rosenhristov.bank.exception.mapper;

import com.rosenhristov.bank.dto.AddressDTO;
import com.rosenhristov.bank.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AddressMapper extends BaseMapper {

    @Autowired
    public AddressMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public AddressDTO toAddressDto(Address entity) {
        log.info("Mapping Address entity to DTO");
        return mapper.map(entity, AddressDTO.class);
    }

    public Address toAddressEntity(AddressDTO dto) {
        log.info("Mapping Address DTO to entity");
        return mapper.map(dto, Address.class);
    }

    public List<AddressDTO> toAddressDtos(List<Address> entities) {
        log.info("Mapping Address entities to DTOs");
        return entities.stream()
                       .map(entity -> toAddressDto(entity))
                       .collect(Collectors.toList());
    }

    public List<Address> toAddressEntities(List<AddressDTO> dtos) {
        log.info("Mapping Address DTOs to entities");
        return dtos.stream()
                   .map(dto -> toAddressEntity(dto))
                   .collect(Collectors.toList());
    }

}
