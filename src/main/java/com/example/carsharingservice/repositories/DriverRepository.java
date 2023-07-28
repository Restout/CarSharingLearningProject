package com.example.carsharingservice.repositories;

import com.example.carsharingservice.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;
@Qualifier("driverRepository")
@Repository
public class DriverRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Driver> getDriverById(String id) throws SQLException {
        StringBuilder request = new StringBuilder();
        request.append("SELECT * ")
                .append("FROM driver ")
                .append("WHERE id LIKE '")
                .append(id)
                .append("'");
        try {
            return Optional.of(namedParameterJdbcTemplate.query(request.toString(), (rs, num) -> Driver.builder()
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .drivingExperience(rs.getInt("driving_experience"))
                    .birthDate(rs.getDate("birth_date"))
                    .passport(rs.getLong("passport"))
                    .build()).get(0));
        } catch (IndexOutOfBoundsException exception) {
            throw new SQLException("No such Element");
        }
    }
}
