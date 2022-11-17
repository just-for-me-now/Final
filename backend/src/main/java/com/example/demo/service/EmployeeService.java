package com.example.demo.service;

import com.example.demo.controller.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee getEmployee(long id) throws EmployeeNotFoundException {

        Optional<Employee> foundEmployee = repo.findById(id);

        if (foundEmployee.isPresent()){

            return foundEmployee.get();
        }
        throw new EmployeeNotFoundException();
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee addEmployee(Employee e) {
        return repo.save(e);
    }

    public void updateEmployee(long id, Employee newData) throws EmployeeNotFoundException {

        Optional<Employee> oldEmployee = repo.findById(id);

        if (!oldEmployee.isPresent()){
            throw new EmployeeNotFoundException();
        }
        newData.setId(id);
        repo.save(newData);

    }

    public void deleteEmployee(long id) throws EmployeeNotFoundException {

        if (!repo.findById(id).isPresent()){
            throw new EmployeeNotFoundException();
        }

        repo.deleteById(id);
    }
}
