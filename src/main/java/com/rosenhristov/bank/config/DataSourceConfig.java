package com.rosenhristov.bank.config;

import lombok.extern.slf4j.Slf4j;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.hikari.minimumIdle:}")
    private int minimumPoolSize;

    @Value("${spring.datasource.hikari.maximumPoolSize:}")
    private int maximumPoolSize;


    @Bean
    public DataSource dataSource() throws SQLException {
        log.info("Initializig DataSource bean");
        PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        dataSource.setURL(url);
        dataSource.setFastConnectionFailoverEnabled(true);
        dataSource.setInitialPoolSize(minimumPoolSize);
        dataSource.setMinPoolSize(minimumPoolSize);
        dataSource.setMaxPoolSize(maximumPoolSize);
        return dataSource;
    }
}

