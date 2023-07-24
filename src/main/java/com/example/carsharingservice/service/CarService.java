package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Iterable<Car> getCarsList(int numberOfPage, int sizeOfPage) {
        numberOfPage -= 1;
        return carRepository.getPagedListOfCars(numberOfPage, sizeOfPage);
    }
}
