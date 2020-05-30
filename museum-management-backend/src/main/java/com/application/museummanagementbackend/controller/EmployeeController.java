package com.application.museummanagementbackend.controller;

import com.application.museummanagementbackend.model.Employee;

import com.application.museummanagementbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String check() {
        return "Employee Details";
    }

    @GetMapping(path = "/employeeList")
    public Employee getAllEmployee() {
        return employeeRepository.getAllEmployee("Priyanka");
    }
}