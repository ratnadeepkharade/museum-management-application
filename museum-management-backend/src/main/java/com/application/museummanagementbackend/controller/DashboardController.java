package com.application.museummanagementbackend.controller;

import com.application.museummanagementbackend.model.MonthlyCount;
import com.application.museummanagementbackend.model.Visitor;
import com.application.museummanagementbackend.repository.DashboardRepository;
import com.application.museummanagementbackend.repository.VisitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/dashboard")
public class DashboardController {

    @Autowired
    VisitorsRepository visitorsRepository;
    @Autowired
    DashboardRepository dashboardRepository;


    @GetMapping
    public String check() {
        return "Visitors Details";
    }

    @GetMapping(path = "/visitorList")
    public List<Visitor> getAllVisitors() {
        return  visitorsRepository.getAllVisitors();
    }

    @GetMapping(path = "/count/{type}")
    public int getCount(@PathVariable int type) {
        return  dashboardRepository.getCount(type);
    }

    @GetMapping(path = "/monthlyCount")
    public List<MonthlyCount> getMonthlyCount() {
        return  dashboardRepository.getMonthlyCount();
    }
}