package com.example.carsharingservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Getter
@Setter
public class RentedCar extends Car {
    private long time;
    private String driverId;
}
