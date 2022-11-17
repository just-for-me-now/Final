package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Controller {

    @Autowired
    EmployeeService service;

    @GetMapping(path = "/employees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(path = "/employees/{id}")
    public Employee getEmployee(@PathVariable long id) throws EmployeeNotFoundException {
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

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee newData)
            throws EmployeeNotFoundException {
        service.updateEmployee(id, newData);

        return (ResponseEntity<Employee>) ResponseEntity.accepted();
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) throws EmployeeNotFoundException {
        service.deleteEmployee(id);

        return (ResponseEntity<Employee>) ResponseEntity.accepted();
    }
}
