package com.example.carsharingservice.repositories;

import com.example.carsharingservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CarRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Iterable<Car> getPagedListOfCars(int numberOfPage, int sizeOfPage) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM car ")
                .append("ORDER BY id ")
                .append("LIMIT ")
                .append(sizeOfPage * numberOfPage)
                .append(",")
                .append(sizeOfPage * (numberOfPage + 1));
        return namedParameterJdbcTemplate.query(query.toString(), (rs, rowNum) -> Car.builder()
                .id(rs.getInt("id"))
                .manufactureYear(rs.getInt("manufacturing_year"))
                .brand(rs.getString("brand"))
                .mileAge(rs.getInt("mileage")).build());
    }

    public Optional<Car> getCarById(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM car ")
                .append("WHERE id = '")
                .append(id)
                .append("'");
        return Optional.ofNullable(namedParameterJdbcTemplate.query(query.toString(), (rs, num) -> Car.builder()
                .id(rs.getInt("id"))
                .brand(rs.getString("brand"))
                .mileAge(rs.getInt("mileage"))
                .manufactureYear(rs.getInt("manufacturing_year"))
                .build()).get(0));
    }

    public boolean deleteCarById(int id) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE ")
                .append("FROM car ")
                .append("WHERE id = ")
                .append(id);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        return namedParameterJdbcTemplate.update(query.toString(), paramMap) == 1;
    }
}
