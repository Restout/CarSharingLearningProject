package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.Driver;
import com.example.carsharingservice.model.RentedCar;
import com.example.carsharingservice.service.CarDriverService;
import com.example.carsharingservice.service.DriverService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public ResponseEntity<Driver> getDriverDataById(@RequestParam @Pattern(regexp = "^[0-9]*$") String id) {
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
    public ResponseEntity<Iterable<Car>> getCarsByDriverId(@RequestParam int driverId) {
        Iterable<Car> carsByDriverId = carDriverService.getCarsByDriverId(driverId);
        if (carsByDriverId == null) {
            ResponseEntity
                    .badRequest()
                    .body(carsByDriverId);
        }
        return ResponseEntity
                .ok()
                .body(carsByDriverId);
    }

    @GetMapping("/carsharing/profile/yourCars/car")
    public ResponseEntity<RentedCar> getDriverRentCarByCarId(@RequestParam int driverId, @RequestParam int carId) {
        Optional<RentedCar> result = carDriverService.getRentCarById(driverId, carId);
        if (result.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(result.get());
    }

    @DeleteMapping("/carsharing/profile/yourCars/car")
    public ResponseEntity<BigDecimal> deleteDriverRentCarById(@RequestParam int driverId, @RequestParam int carId) {
        BigDecimal result;
        try {
            result = carDriverService.getChargeForRentTime(driverId, carId);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(result.setScale(2, RoundingMode.HALF_EVEN));
    }
   /* @PostMapping("/carsharing/profile/yourCars/car")
    public ResponseEntity<Boolean> rentAvailableCar(@RequestParam int driverId,@RequestParam int carId){
        return ResponseEntity
                .ok()
                .body(carDriverService.rentAvailableCar(driverId,carId));
    }*/
}

