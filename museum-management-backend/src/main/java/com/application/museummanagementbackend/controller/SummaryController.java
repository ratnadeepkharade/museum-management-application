package com.application.museummanagementbackend.controller;



import com.application.museummanagementbackend.model.Summary;
import com.application.museummanagementbackend.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/summary")
public class SummaryController {
    @Autowired
    SummaryRepository summaryRepository;

    @GetMapping
    public String check() {
        return "Summary Details";
    }

    @GetMapping(path = "/addsummaryList")
    public List<Summary> getAllSummary() {
        return  summaryRepository.getAllSummary();
    }

}
