package com.ust.efx.poc.usermanagement.controller;

import com.ust.efx.poc.usermanagement.exception.ResourceNotFoundException;
import com.ust.efx.poc.usermanagement.model.Employee;
import com.ust.efx.poc.usermanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by niran on 27/04/19.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    // @PutMapping("/employees/{id}")
    // public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           // @Valid @RequestBody Employee employeeDetails) {

        // Employee employee = employeeRepository.findById(employeeId)
                // .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        // employee.setTitle(employeeDetails.getTitle());
        // employee.setContent(employeeDetails.getContent());

        // Employee updatedEmployee = employeeRepository.save(employee);
        // return updatedEmployee;
    // }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }
}
