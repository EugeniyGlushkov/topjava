package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcMealRepositoryConfig {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcMealRepositoryConfig(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Bean
    @Profile("postgres")
    public PostgresJdbcMealRepositoryImpl postgresManager() {
        return new PostgresJdbcMealRepositoryImpl(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Bean
    @Profile("hsqldb")
    public JdbcMealRepositoryImpl hsqldbManager() {
        return new PostgresJdbcMealRepositoryImpl(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }
}
