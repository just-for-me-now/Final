package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    EmployeeService service;

    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(path = "/employees/{id}")
    public Employee getEmployee(@PathVariable long id) {
        return service.getEmployee(id);
    }

    @PostMapping(path = "/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {
        service.addEmployee(e);
    }
}
