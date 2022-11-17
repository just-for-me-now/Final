package com.example.demo.prepopulator;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class Prepopulator implements CommandLineRunner {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public void run(String... args) throws Exception {
        Employee employee1 = new Employee("John","Doe","979-490-742","john@doe.com");
        Employee employee2 = new Employee("Jane","Dew","937-275-723","jane@dew.com");
        Employee employee3 = new Employee("Kevin","Malone","973-627-183","kevin@malone.com");
        repo.save(employee1);
        repo.save(employee2);
        repo.save(employee3);
        
    }
}
