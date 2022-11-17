package com.example.demo.repository;

import com.example.demo.models.Employee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public List<Employee> findAll();
}
