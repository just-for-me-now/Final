package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.NotFound;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(max = 25, min = 2, message = "first name cannot be longer than 25 characters")
    private String firstName;

    @NotNull
    @Size(max = 25, min = 2, message = "first name cannot be longer than 25 characters")
    private String lastName;

    @NotNull
    @Size(max = 20, min = 2, message = "phone number cannot be longer than 20 characters")
    @Pattern(regexp = "^(\\d{3}[- ]?){3}$")
    private String phoneNumber;

    @NotNull
    @Size(max = 25, message = "email cannot be longer than 25 characters")
    @Pattern(regexp ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6})$")
    private String email;

    public Employee(String firstName, String lastName, String phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Employee(){
        
    }

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

}
