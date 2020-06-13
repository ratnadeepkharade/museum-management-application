package com.application.museummanagementbackend.controller;

import com.application.museummanagementbackend.model.Section;
import com.application.museummanagementbackend.model.Visitor;
import com.application.museummanagementbackend.repository.SectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/sections")
public class SectionsController {

    @Autowired
    SectionsRepository sectionsRepository;

    @GetMapping
    public String check() {
        return "Sections Details";
    }

    @GetMapping(path = "/sectionList")
    public List<Section> getAllSections() {
        return sectionsRepository.getAllSections();
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public String addVisitor(@RequestBody Section section) {
        sectionsRepository.saveSection(section);
        System.out.println("Adding :"+section);
        return "added " + section;
    }

    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String updateVisitor(@RequestBody Section section) {
        sectionsRepository.updateSection(section);
        System.out.println("Updating :"+section);
        return "Updated " + section;
    }

    @DeleteMapping(path = "/delete/{sectionId}")
    public String deleteEmployee(@PathVariable int sectionId) {
        sectionsRepository.deleteSection(sectionId);
        System.out.println("Updating :"+sectionId);
        return "Updated " + sectionId;
    }
}
