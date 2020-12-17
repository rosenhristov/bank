package com.rosenhristov.bank.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

@Configuration
public class ETagFilterConfig {

    @Bean
    public FilterRegistrationBean getEtagClintRequestFilter() {
        FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean
                = new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
        filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC));
        filterRegistrationBean.addUrlPatterns("/bank/*");
        filterRegistrationBean.setName("ETagFilter");
        return filterRegistrationBean;
    }
}
