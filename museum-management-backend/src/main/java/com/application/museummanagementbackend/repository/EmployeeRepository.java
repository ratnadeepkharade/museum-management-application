package com.application.museummanagementbackend.repository;

import com.application.museummanagementbackend.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee>  getAllEmployee() {
        String sql = "SELECT * FROM employeewithsectionName";

         List<Employee> employees = (List<Employee>) jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Employee.class));

        return employees;


    }
}
