package com.example.carsharingservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
 @Pattern(regexp="[0-9]")
   private String id;
    private String firstName;
    private String lastName;
    private int drivingExperience;
    private Date birthDate;
    @Size(min=11,max=11)
    private long passport;
}
