package com.ust.efx.poc.usermanagement.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the usermanagement application. You can create a new Employee by making a POST request to /api/employees endpoint.";
    }
}
