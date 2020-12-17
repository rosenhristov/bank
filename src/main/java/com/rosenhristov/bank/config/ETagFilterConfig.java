package com.rosenhristov.bank.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class ETagFilterConfig {

    @Bean
    public FilterRegistrationBean getEtagClintRequestFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean
                = new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
        filterRegistrationBean.addUrlPatterns("/bank/*");
        filterRegistrationBean.setName("etagFilter");
        return filterRegistrationBean;
    }
}
