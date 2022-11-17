package com.example.demo.api.tests;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ApiTests {

    @Test
    public void getSpecificEmployeeReturnsAnEmployee() {

    }

    @Test
    public void getSpecificEmployeeWithWrongIdReturns404() {

    }

    @Test
    public void getSpecificEmployeeWithWrongEmailReturns500() {

    }

    @Test
    public void getAllEmployeesReturnsTheCorrectNumberOfEmployees() {

    }

    @Test
    public void getAllEmployeesReturnsTheEmployeesWithTheCorrectFormat() {

    }

    @ParameterizedTest
    @MethodSource("wrongEmployees")
    public void postEmployeeReturnsCreatedWithAValidLocationHeaderWithTheData() {

    }

    @Test
    public void postEmployeeWithWrongValuesReturnsA500() {

    }

    @Test
    public void postEmployeeWithEmptyValuesReturnsA500() {

    }

    @Test
    public void putEmployeeReturnsA202AndChangesTheValues() {

    }

    @Test
    public void putEmployeeWithWrongOrEmptyJSONReturns500() {

    }

    @Test
    public void putEmployeeWithWrongIdReturns404() {

    }

    @Test
    public void deleteEmployeeReturnsA202AndDeletesTheValues() {

    }

    @Test
    public void deleteEmployeeWithWrongIdReturns404() {

    }

    public List<Arguments> wrongEmployees() {
        return null;
    }
}
