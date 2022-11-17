package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.NotFound;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    @JsonProperty("first-name")
    @NotNull
    @Max(value = 25, message = "first name cannot be longer than 25 characters")
    private String firstName;

    @JsonProperty("last-name")
    @NotNull
    @Max(value = 25,message = "first name cannot be longer than 25 characters")
    private String lastName;

    @JsonProperty("phone-number")
    @NotNull
    @Max(value = 20, message = "phone number cannot be longer than 20 characters")
    @Pattern(regexp = "^(\\d{3}[- ]?){3}$")
    private String phoneNumber;

    @JsonProperty("email")
    @NotNull
    @Max(value = 25, message = "email cannot be longer than 25 characters")
    @Pattern(regexp = "\"\\\\b[\\\\w.%-]+@[-.\\\\w]+\\\\.[A-Za-z]{2,6}\\\\b\"")
    private String email;

    public Employee(String firstName, String lastName, String phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

}
