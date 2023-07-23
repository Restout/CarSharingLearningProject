package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Driver;
import com.example.carsharingservice.repositories.DriverRepository;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DriverController {
    @Autowired
    DriverRepository driverRepository;

    @GetMapping("/carsharing/profile/user")
    public ResponseEntity<Driver> getDriverDataById(@RequestParam @Pattern(regexp = "/^[0-9]*$/") String id) {
        Optional<Driver> driver = driverRepository.getDriverById(id);
        if (driver.isEmpty()) {
            return ResponseEntity
                    .status(402)
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(driver.get());
    }
}

