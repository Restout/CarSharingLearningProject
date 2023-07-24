package com.example.carsharingservice.controller;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
