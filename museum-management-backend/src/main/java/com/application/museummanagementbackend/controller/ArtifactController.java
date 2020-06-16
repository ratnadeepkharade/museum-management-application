package com.application.museummanagementbackend.controller;

import com.application.museummanagementbackend.model.Artifact;
import com.application.museummanagementbackend.model.Employee;
import com.application.museummanagementbackend.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/artifacts")
public class ArtifactController {
    @Autowired
    ArtifactRepository artifactRepository;

    @GetMapping
    public String check() {
        return "Artifact Details";
    }

    @GetMapping(path = "/artifactList")
    public List<Artifact> getAllArtifact() {
        return  artifactRepository.getAllArtifact();
    }

    @DeleteMapping(path = "/delete/{artifactsID}")
    public String deleteArtifact(@PathVariable int artifactsID) {

        artifactRepository.deleteArtifact(artifactsID);
        System.out.println("Deleteing :"+artifactsID);
        return "Deleted " + artifactsID;
    }
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public String addArtifact(@RequestBody Artifact artifact) {

        artifactRepository.save(artifact);


        System.out.println("Adding :"+artifact);
        return "added " + artifact;
    }
    @PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String updateArtifact(@RequestBody Artifact artifact) {
        artifactRepository.update( artifact);
        System.out.println("Updating :"+ artifact);
        return "Updated " +  artifact;
    }

}
