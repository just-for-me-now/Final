package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    private String firtname;

    private String lastName;

    private String phoneNumber;

    private String email;

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

}
