package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
    private String firstName;

    @JsonProperty("last-name")
    @NotNull
    private String lastName;

    @JsonProperty("phone-number")
    @NotNull
    private String phoneNumber;

    @JsonProperty("email")
    @NotNull
    private String email;

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

}
