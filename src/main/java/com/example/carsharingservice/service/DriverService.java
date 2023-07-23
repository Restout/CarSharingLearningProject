package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Driver;
import com.example.carsharingservice.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Optional<Driver> getDriverById(String id) {
        return driverRepository.getDriverById(id);
    }
}
