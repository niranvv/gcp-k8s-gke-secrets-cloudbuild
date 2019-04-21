package com.ust.efx.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Add imports
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
  @GetMapping("/")
  public String hello() {
    return "Hello and Welcome to the employee application. You can create a new Employee by making a POST request to /api/employees endpoint.";
  }
}
