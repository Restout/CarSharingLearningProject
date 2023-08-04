package com.example.carsharingservice.security.model;

import lombok.*;


@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
public class UserPassword {
    private String username;
    private char[] password;
}
