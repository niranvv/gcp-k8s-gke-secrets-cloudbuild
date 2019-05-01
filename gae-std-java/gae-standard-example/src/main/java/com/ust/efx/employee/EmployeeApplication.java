package com.ust.efx.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Add imports
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class EmployeeApplication {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeApplication.class.getName());

    public static void main(String[] args) {
        logger.info("Logging INFO with Logback");
        //logger.error("Logging ERROR with Logback");
        SpringApplication.run(EmployeeApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        logger.info("Logging INFO with Logback - / root(hello)");
        return "Hello and Welcome to the employee application. You can create a new Employee by making a POST request to /api/employees endpoint.";
    }
}
