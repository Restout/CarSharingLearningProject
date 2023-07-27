package com.example.carsharingservice.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Car {
    private int id;
    private String brand;
    @Pattern(regexp = "^(19\\d\\d|20[0-9]\\d|29\\d\\d)$")
    private int manufactureYear;
    @Positive
    private int mileAge;

}
