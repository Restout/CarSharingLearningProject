package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Driver;
import com.example.carsharingservice.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;
@Qualifier("driverService")
@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Optional<Driver> getDriverById(String id) {
        try {
            return driverRepository.getDriverById(id);
        } catch (SQLException exception) {
            return Optional.empty();
        }
    }
}
