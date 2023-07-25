package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/available_cars/data")
    public ResponseEntity<Iterable<Car>> getAvailableCars(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(carService.getCarsList(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping("/available_cars/{car_id}")
    public ResponseEntity<Optional<Car>> getCatById(@PathVariable("car_id") int id) {
        Optional<Car> car = carService.getCarById(id);
        if (car.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(car);

    }

    @DeleteMapping("/available_cars/{car_id}")
    public ResponseEntity<Boolean> deleteCatById(@PathVariable("car_id") int id) {
        boolean deleteAnswer= carService.deleteCarById(id);
        if(!deleteAnswer){
            return ResponseEntity.badRequest().body(deleteAnswer);
        }
        return ResponseEntity.ok().body(deleteAnswer);
    }
}
