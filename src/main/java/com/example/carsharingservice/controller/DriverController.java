package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.Driver;
import com.example.carsharingservice.service.CarDriverService;
import com.example.carsharingservice.service.DriverService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DriverController {
    @Qualifier("driverService")
    @Autowired
    DriverService driverService;
    @Qualifier("carDriverService")
    @Autowired
    CarDriverService carDriverService;

    @GetMapping("/carsharing/profile/user")
    public ResponseEntity<Driver> getDriverDataById(@RequestParam @Pattern(regexp = "/^[0-9]*$/") String id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isEmpty()) {
            return ResponseEntity
                    .status(402)
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(driver.get());
    }

    @GetMapping("/carsharing/profile/yourCars")
    public ResponseEntity<Iterable<Car>> getCarsByDriverId(@RequestParam int id) {
        Iterable<Car> carsByDriverId = carDriverService.getCarsByDriverId(id);
        if (carsByDriverId == null) {
            ResponseEntity
                    .badRequest()
                    .body(carsByDriverId);
        }
        return ResponseEntity
                .ok()
                .body(carsByDriverId);
    }

}

