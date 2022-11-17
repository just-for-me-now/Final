package com.example.demo.service;

import com.example.demo.models.Employee;
import com.example.demo.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository repo;

    public Employee getEmployee(long id) {

        Optional<Employee> foundEmployee = repo.findById(id);

        if (foundEmployee.isPresent()){

            return foundEmployee.get();
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAllEmployees() {

    }

    public Employee addEmployee(Employee e) {
    }
}
