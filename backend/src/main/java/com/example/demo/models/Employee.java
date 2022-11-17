package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int Id;

    private String firtname;

    private String lastName;

    private String phoneNumber;

    private String email;

}
