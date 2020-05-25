package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public LoginUser getLoggedInUser(String id) {
        String sql = "SELECT * FROM user WHERE firstName=?";

        LoginUser city = (LoginUser) jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper(LoginUser.class));

        return city;

        //LoginUser loginUser;
        //loginUser = jdbcTemplate.queryForObject("select firstName from user where firstName='John'", String.class);
        //return loginUser;
    }
}
