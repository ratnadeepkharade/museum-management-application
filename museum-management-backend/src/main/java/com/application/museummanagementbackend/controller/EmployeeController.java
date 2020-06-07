package com.application.museummanagementbackend.controller;
import com.application.museummanagementbackend.model.Employee;
import com.application.museummanagementbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Employee>  getAllEmployee() {
        return  employeeRepository.getAllEmployee();
    }
    @DeleteMapping(path = "/deleteEmployee/{empId}")
    public void deleteEmployee(@PathVariable int empId) {

        employeeRepository.deleteEmployee(empId);
    }
}