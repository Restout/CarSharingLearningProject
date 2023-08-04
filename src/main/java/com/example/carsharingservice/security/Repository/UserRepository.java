package com.example.carsharingservice.security.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<UserDetails> getUserByUserName(String usernameId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ")
                .append("FROM users ")
                .append("WHERE userId LIKE ")
                .append(usernameId);
        return namedParameterJdbcTemplate.query(query.toString(), (rs, rowNum) -> User.builder()
                        .username(rs.getString("userId"))
                        .password(rs.getString("password"))
                        .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                        .build())
                .stream()
                .findFirst();
    }
}
