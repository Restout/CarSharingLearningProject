package com.example.carsharingservice.security.dao;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Getter
public class UserDao {
    private final List<UserDetails> USER_DATA = Arrays.asList(new User(
            "123",
            "Password",
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));

    public UserDetails findUserByUsername(String username) {
        return USER_DATA
                .stream()
                .filter(x -> x.getUsername().equals(username))
                .collect(Collectors.toList()).get(0);
    }
}
