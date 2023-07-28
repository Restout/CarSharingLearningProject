package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/carsharing/available_cars/data")
    public ResponseEntity<Iterable<Car>> getAvailableCars(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(carService.getCarsList(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping("/carsharing/available_cars/{car_id}")
    public ResponseEntity<Optional<Car>> getCarById(@PathVariable("car_id") int id) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .body(car);
        }
        return ResponseEntity
                .ok()
                .body(car);

    }

    @DeleteMapping("/carsharing/available_cars/{car_id}")
    public ResponseEntity<Boolean> deleteCatById(@PathVariable("car_id") int id) {
        boolean deleteAnswer = carService.deleteCarById(id);
        if (!deleteAnswer) {
            return ResponseEntity
                    .badRequest()
                    .body(deleteAnswer);
        }
        return ResponseEntity
                .ok()
                .body(deleteAnswer);
    }

    @PostMapping("carsharing/available_cars/data")
    public ResponseEntity<Boolean> addNewCar(@RequestBody Car car) {
        boolean updateAnswer = carService.addNewCar(car);
        if (!updateAnswer) {
            return ResponseEntity
                    .badRequest()
                    .body(updateAnswer);
        }
        return ResponseEntity
                .ok()
                .body(updateAnswer);
    }
}
