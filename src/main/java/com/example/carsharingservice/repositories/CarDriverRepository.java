package com.example.carsharingservice.repositories;

import com.example.carsharingservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Qualifier("carDriverRepository")
@Repository
public class CarDriverRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Iterable<Car> getCarsByDriverId(int id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM car_driver ")
                .append("JOIN car ")
                .append("ON car.id=car_driver.car_id ")
                .append("WHERE driver_id = ")
                .append(id);
         return  namedParameterJdbcTemplate.query(query.toString(), (rs, rowNum) -> Car.builder()
                .id(rs.getInt("car_id"))
                .brand(rs.getString("brand"))
                .manufactureYear(rs.getInt("manufacturing_year"))
                .mileAge(rs.getInt("mileage")).build());
    }
}
