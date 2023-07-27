package com.example.carsharingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CarsharingServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CarsharingServiceApplication.class, args);
        /*TestRepository rep= (TestRepository) context.getBean("testRepository");
        rep.setNewDriver();*/
    }

}
