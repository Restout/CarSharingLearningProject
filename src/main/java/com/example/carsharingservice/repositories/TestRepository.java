package com.example.carsharingservice.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository("testRepository")
public class TestRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int setNewDriver() {
        Random random = new Random();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", String.valueOf(random.nextInt()));
        parameters.put("first_name", "Vasya");
        parameters.put("last_name", "Sheglov");
        parameters.put("driving_experience", 11);
        parameters.put("birth_date", "2023-11-11");
        parameters.put("passport", 1123);

        return namedParameterJdbcTemplate.update("INSERT INTO driver (id,first_name,last_name,driving_experience,birth_date,passport) values(:id,:first_name,:last_name,:driving_experience,:birth_date,:passport)", parameters);
    }
}
