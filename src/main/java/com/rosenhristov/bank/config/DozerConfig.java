package com.rosenhristov.bank.config;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper getDozerMapper() {
        log.info("Initializing DozerBeanMapper bean");
        return new DozerBeanMapper();
    }
}
