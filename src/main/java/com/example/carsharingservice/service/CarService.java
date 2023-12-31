package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Iterable<Car> getCarsList(int numberOfPage, int sizeOfPage) {
        numberOfPage -= 1;
        return carRepository.getPagedListOfCars(numberOfPage, sizeOfPage);
    }

    public Optional<Car> getCarById(int id) {
        try {
            return carRepository.getCarById(id);
        } catch (SQLException sqlException) {
            return Optional.empty();
        }
    }

    public boolean deleteCarById(int id) {
        return carRepository.deleteCarById(id);
    }

    public boolean addNewCar(Car car) {
        return carRepository.addNewCar(car);
    }
}
