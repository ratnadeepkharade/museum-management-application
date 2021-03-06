package com.application.museummanagementbackend.controller;

import com.application.museummanagementbackend.model.LoginUser;
import com.application.museummanagementbackend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/login")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;

    @GetMapping
    public String check() {
        return "LOGIN API";
    }

    @GetMapping(path = "/user")
    public LoginUser getUser() {
        return loginRepository.getLoggedInUser("John");
    }
}
