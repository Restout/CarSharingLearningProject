package com.example.carsharingservice;

import com.example.carsharingservice.repositories.TestRepository;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class CarsharingServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CarsharingServiceApplication.class, args);

        TestRepository rep= (TestRepository) context.getBean("testRepository");
        rep.setNewDriver();
    }

}
