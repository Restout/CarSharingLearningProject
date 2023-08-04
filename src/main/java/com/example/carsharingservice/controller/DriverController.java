package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.Driver;
import com.example.carsharingservice.model.RentedCar;
import com.example.carsharingservice.security.JwtUtils;
import com.example.carsharingservice.service.CarDriverService;
import com.example.carsharingservice.service.DriverService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    JwtUtils jwtUtils;

    //reges dont work why??
    @GetMapping("/carsharing/profile/user")
    public ResponseEntity<Driver> getDriverDataById(@RequestParam @Pattern(regexp = "^[0-9]*$") String driverId, HttpServletRequest request) {
        if (!validTokenUserId(driverId, request)) {
            return ResponseEntity.status(401).build();
        }
        Optional<Driver> driver = driverService.getDriverById(driverId);
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
    public ResponseEntity<Iterable<Car>> getCarsByDriverId(@RequestParam String driverId, HttpServletRequest request) {//добавить пагинацию
        if (!validTokenUserId(driverId, request)) {
            return ResponseEntity.status(401).build();
        }
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
    public ResponseEntity<RentedCar> getDriverRentCarByCarId(@RequestParam String driverId, @RequestParam int carId, HttpServletRequest request) {
        if (!validTokenUserId(driverId, request)) {
            return ResponseEntity.status(401).build();
        }
        Optional<RentedCar> result = carDriverService.getRentCarById(driverId, carId);
        if (result.isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(result.get());
    }

    @DeleteMapping("/carsharing/profile/yourCars/car")
    public ResponseEntity<BigDecimal> deleteDriverRentCarById(@RequestParam String driverId, @RequestParam int carId, HttpServletRequest request) {
        if (!validTokenUserId(driverId, request)) {
            return ResponseEntity.status(401).build();
        }
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

    @PostMapping("/carsharing/profile/yourCars/car")
    public ResponseEntity<Boolean> rentAvailableCar(@RequestParam String driverId, @RequestParam int carId, HttpServletRequest request) {
        if (!validTokenUserId(driverId, request)) {
            return ResponseEntity.status(401).build();
        }
        boolean result = carDriverService.rentAvailableCar(driverId, carId);
        if (!result) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .ok()
                .body(result);
    }

    private boolean validTokenUserId(String driverId, HttpServletRequest request) {
        String jwtToken = request.getHeaders(HttpHeaders.AUTHORIZATION).nextElement();
        String username = jwtUtils.extractUsername(jwtToken.substring(7));
        return driverId.equals(username);
    }
}

