package com.example.carsharingservice;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
@Configuration
public class ApplicationConfig {
    @Bean("mariaDB")
    public DataSource mariaDBDataSource() throws SQLException {
        MariaDbDataSource mariaDbDataSource = new MariaDbDataSource();
        mariaDbDataSource.setUrl("jdbc:mariadb://127.0.0.1:3309/carsharing");
        mariaDbDataSource.setUser("root");
        mariaDbDataSource.setPassword("root");
        return mariaDbDataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource mariaDbDataSource){
        return new JdbcTemplate(mariaDbDataSource);
    }
    @Bean
    public NamedParameterJdbcTemplate myNamedParameterJdbcTemplate(DataSource mariaDbDataSource){
        return new NamedParameterJdbcTemplate(mariaDbDataSource);
    }
}
