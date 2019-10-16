package com.poc.demok8ssecrets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;

@RestController
public class WelcomeController {
    @Value("${secretdemo.username:no_values_given_for_username_in_app_prop}")
    private String username;

    @Value("${secretdemo.password:no_values_given_for_password_in_app_prop}")
    private String password;

    @GetMapping("/")
    public String home() {
        return String.format("username:[%s]. Password:[%s]", username ,password);
    }

}
