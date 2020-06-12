package com.application.museummanagementbackend.controller;
import com.application.museummanagementbackend.model.Employee;
import com.application.museummanagementbackend.model.Visitor;
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
    @DeleteMapping(path = "/delete/{empId}")
    public String deleteEmployee(@PathVariable int empId) {

        employeeRepository.deleteEmployee(empId);
        System.out.println("Deleteing :"+empId);
        return "Deleted " + empId;
    }
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
     public String addEmployee(@RequestBody Employee employee) {

        employeeRepository.save(employee);
        System.out.println("Adding :"+employee);
        return "added " + employee;
    }
    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String updateEmployee(@RequestBody Employee employee) {
        employeeRepository.update( employee);
        System.out.println("Updating :"+ employee);
        return "Updated " +  employee;
    }

}