package com.application.museummanagementbackend.controller;

import com.application.museummanagementbackend.model.Visitor;
import com.application.museummanagementbackend.repository.VisitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/visitors")
public class VisitorsController {

    @Autowired
    VisitorsRepository visitorsRepository;

    @GetMapping
    public String check() {
        return "Visitors Details";
    }

    @GetMapping(path = "/visitorList")
    public List<Visitor> getAllVisitors() {
        return  visitorsRepository.getAllVisitors();
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    String addVisitor(@RequestBody Visitor visitor) {

        visitorsRepository.save(visitor);
        System.out.println("Adding :"+visitor);
        return "added " + visitor;
    }
}