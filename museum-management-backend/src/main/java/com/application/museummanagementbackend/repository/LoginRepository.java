package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getLoggedInUser() {
        String loginUser;
        loginUser = jdbcTemplate.queryForObject("select firstName from user where firstName='John'", String.class);
        return loginUser;
    }
}
