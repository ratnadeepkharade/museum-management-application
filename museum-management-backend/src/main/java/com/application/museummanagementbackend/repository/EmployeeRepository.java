package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Employee;
import com.application.museummanagementbackend.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee getAllEmployee(String id) {
        String sql = "SELECT * FROM employee where firstName=?";

         Employee emp = (Employee) jdbcTemplate.queryForObject(sql,new Object[]{id},
                new BeanPropertyRowMapper(com.application.museummanagementbackend.model.Employee.class));

        return emp;


    }
}
