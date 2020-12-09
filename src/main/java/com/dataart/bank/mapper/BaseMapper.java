package com.dataart.bank.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class BaseMapper {

    protected final DozerBeanMapper mapper;

    public BaseMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
}
