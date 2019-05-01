package com.ust.efx.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ust.efx.employee.exception.ResourceNotFoundException;
import com.ust.efx.employee.model.Employee;
import com.ust.efx.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class.getName());


    @Autowired
    EmployeeRepository employeeRepository;

//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }

    @GetMapping("/employees")
    public Page<Employee> getAllEmployees(@RequestParam int pagenumber,@RequestParam int size) {
        logger.info(String.format("Logging INFO with Logback. Method name:api=getAllEmployees;pagenumber=%s;size=%s;", pagenumber, size));
        Pageable pageable = PageRequest.of(pagenumber, size);
        Page<Employee> pagedEmployees = employeeRepository.findAll(pageable);
        return pagedEmployees;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        logger.info(String.format("Logging INFO with Logback. Method name:api=createEmployee"));
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        logger.info(String.format("Logging INFO with Logback. Method name:api=getEmployeeById"));
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
        logger.info(String.format("Logging INFO with Logback. Method name:api=deleteEmployee"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }
}
