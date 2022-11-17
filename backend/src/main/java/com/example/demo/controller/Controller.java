package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin
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
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee e) throws ValidationException {
        Employee returnedEmployee = service.addEmployee(e);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(returnedEmployee.getId()).toUri();

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @Valid @RequestBody Employee newData)
            throws EmployeeNotFoundException, ValidationException {
        service.updateEmployee(id, newData);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) throws EmployeeNotFoundException {
        service.deleteEmployee(id);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
