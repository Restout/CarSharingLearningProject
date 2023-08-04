package com.example.carsharingservice.service;

import com.example.carsharingservice.model.Car;
import com.example.carsharingservice.model.RentedCar;
import com.example.carsharingservice.repositories.CarDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Qualifier("carDriverService")
@Service
public class CarDriverService {
    @Autowired
    CarDriverRepository carDriverRepository;
    private final double chargeMultiplier = 0.00015d;

    public Iterable<Car> getCarsByDriverId(String id) {
        return carDriverRepository.getCarsByDriverId(id);

    }

    public Optional<RentedCar> getRentCarById(String driverId, int carId) {
        try {
            return carDriverRepository.getRentCarById(driverId, carId);
        } catch (SQLException sqlException) {
            return Optional.empty();
        }
    }

    public BigDecimal getChargeForRentTime(String driverId, int carId) throws Exception {
        Date curentDate = new Date();
        Optional<RentedCar> deletedCar = carDriverRepository.getRentCarById(driverId, carId);
        if (deletedCar.isEmpty()) {
            throw new Exception();
        }
        carDriverRepository.deleteRentCarById(carId);
        return BigDecimal.valueOf(Math.abs(curentDate.getTime() - deletedCar.get().getTime()) * chargeMultiplier);
    }
    public boolean rentAvailableCar(String driverId,int carId){
        return carDriverRepository.insertNewCarForDriver(driverId,carId);
    }
}
