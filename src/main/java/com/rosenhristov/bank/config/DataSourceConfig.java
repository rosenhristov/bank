package com.rosenhristov.bank.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.PrintWriter;
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
    private int minimumIdle;

    @Value("${spring.datasource.hikari.maximumPoolSize:}")
    private int maximumPoolSize;


    @Bean
    public DataSource dataSource() throws SQLException {
        log.info("Initializig DataSource bean");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setLogWriter(new PrintWriter(System.out));
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMinimumIdle(minimumIdle);

        return dataSource;
    }
}

