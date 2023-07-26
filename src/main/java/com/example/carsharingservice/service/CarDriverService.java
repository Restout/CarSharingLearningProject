package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.repositories.CarDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Qualifier("carDriverService")
@Service
public class CarDriverService {
    @Autowired
    CarDriverRepository carDriverRepository;

    public Iterable<Car> getCarsByDriverId(int id) {
        return carDriverRepository.getCarsByDriverId(id);

    }
}
