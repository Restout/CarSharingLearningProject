package com.example.carsharingservice.repositories;

import com.example.carsharingservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
                .manufactureYear(rs.getInt("manufacture_year"))
                .brand(rs.getString("brand"))
                .mileAge(rs.getInt("mileage")).build());
    }
}
