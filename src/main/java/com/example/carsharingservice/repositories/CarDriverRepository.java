package com.example.carsharingservice.repositories;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.RentedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Qualifier("carDriverRepository")
@Repository
public class CarDriverRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Iterable<Car> getCarsByDriverId(int driverId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM car_driver ")
                .append("JOIN car ")
                .append("ON car.id=car_driver.car_id ")
                .append("WHERE driver_id = ")
                .append(driverId);
        return namedParameterJdbcTemplate.query(query.toString(), (rs, rowNum) -> Car.builder()
                .id(rs.getInt("car_id"))
                .brand(rs.getString("brand"))
                .manufactureYear(rs.getInt("manufacturing_year"))
                .mileAge(rs.getInt("mileage")).build());
    }

    public Optional<RentedCar> getRentCarById(String driverId, int carId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM car_driver ")
                .append("JOIN car ")
                .append("ON car.id=car_id ")
                .append("WHERE car_id =")
                .append(carId)
                .append(" AND driver_id = ")
                .append(driverId);
        try {
            return Optional.of(namedParameterJdbcTemplate.query(query.toString(), (rs, rowNum) -> RentedCar.builder()
                    .time(rs.getLong("rent_start_time"))
                    .driverId(rs.getString("driver_id"))
                    .brand(rs.getString("brand"))
                    .manufactureYear(rs.getInt("manufacturing_year"))
                    .id(rs.getInt("car_id"))
                    .build()).get(0));
        } catch (IndexOutOfBoundsException exception) {
            throw new SQLException("No such Element");
        }
    }

    public boolean deleteRentCarById(int carId) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE ")
                .append("FROM car_driver ")
                .append("WHERE car_id = ")
                .append(carId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("car_id", carId);
        return namedParameterJdbcTemplate.update(query.toString(), paramMap) == 1;
    }

    public boolean insertNewCarForDriver(String driverId, int carId) {
        Date currentDate = new Date();
        HashMap<String, Object> rentParamsMap = new HashMap<>();
        rentParamsMap.put("carId", carId);
        rentParamsMap.put("driverId", driverId);
        rentParamsMap.put("id", carId+driverId);
        rentParamsMap.put("startTime", currentDate.getTime());
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO car_driver ")
                .append("(car_id,driver_id,id,rent_start_time) ")
                .append("values ")
                .append("(:carId,:driverId,:id,:startTime) ");
        return namedParameterJdbcTemplate.update(query.toString(), rentParamsMap) == 1;
    }
}
