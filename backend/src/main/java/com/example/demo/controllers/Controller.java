package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.service.EmployeeService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        Employee returnedEmployee = service.addEmployee(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(returnedEmployee.getId()).toUri();

        return (ResponseEntity<Employee>) ResponseEntity.created(location);
    }
}
