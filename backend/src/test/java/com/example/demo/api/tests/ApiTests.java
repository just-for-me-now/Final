package com.example.demo.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.Employee;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ApiTests {

    @Test
    public void getSpecificEmployeeReturnsAnEmployee() {
        Employee e = when().get("http://localhost:8080/employees/1")
                .then().assertThat().statusCode(200)
                .extract().body().as(Employee.class);

        Employee expected = new Employee("John", "Doe", "979-490-742", "john@doe.com");
        expected.setId(1);

        assertEquals(expected, e);
    }

    @Test
    public void getSpecificEmployeeWithWrongIdReturns404() {
        when().get("http://localhost:8080/employees/1000000")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void getAllEmployeesReturnsTheCorrectNumberOfEmployees() {
        Employee[] employees = when().get("http://localhost:8080/employees")
                .then().assertThat().statusCode(200)
                .extract().body().as(Employee[].class);

        assertEquals(3, employees.length);
    }

    @Test
    public void postEmployeeReturnsCreatedWithAValidLocationHeaderWithTheData() {
        Employee e = new Employee("John", "Carpenter", "123-123-123", "john@carpenter.com");
        String location = given().contentType(ContentType.JSON).body(e)
                .when().post("http://localhost:8080/employees")
                .then().assertThat().statusCode(201).extract().header("location");

        Employee received = when().get(location).then().extract().as(Employee.class);
        e.setId(received.getId());
        assertEquals(e, received);
    }

    @ParameterizedTest
    @MethodSource("wrongEmployees")
    public void postEmployeeWithWrongValuesReturnsA500(long id, Employee employee) {
        given().contentType(ContentType.JSON).body(employee)
                .when().post("http://localhost:8080/employees")
                .then().assertThat().statusCode(500);
    }

    @Test
    public void putEmployeeReturnsA202AndChangesTheValues() {
        Employee e = new Employee("John", "Carpenter", "123-123-123", "john@carpenter.com");
        String location = given().contentType(ContentType.JSON).body(e)
                .when().post("http://localhost:8080/employees")
                .then().assertThat().statusCode(201).extract().header("location");

        e = new Employee("Michael", "Fassbender", "123-123-123", "michael@we.com");
        given().contentType(ContentType.JSON).body(e).when().put(location).then().assertThat().statusCode(202);
        Employee received = when().get(location).then().extract().as(Employee.class);
        e.setId(received.getId());
        assertEquals(e, received);
    }

    @ParameterizedTest
    @MethodSource("wrongEmployees")
    public void putEmployeeWithWrongOrEmptyJSONReturns500(long id, Employee employee) {
        Employee expected = new Employee("John", "Doe", "979-490-742", "john@doe.com");
        expected.setId(1);

        given().contentType(ContentType.JSON).body(employee).when().put("http://localhost:8080/employees" + id)
                .then().assertThat().statusCode(500);

        Employee received = when().get("http://localhost:8080/employees" + id)
                .then().extract().as(Employee.class);
        assertEquals(expected, received);
    }

    @Test
    public void putEmployeeWithWrongIdReturns404() {
        when().put("http://localhost:8080/employees/1000000")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void deleteEmployeeReturnsA202AndDeletesTheValues() {
        when().delete("http://localhost:8080/employees/1")
                .then().assertThat().statusCode(202);
        when().get("http://localhost:8080/employees/1")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void deleteEmployeeWithWrongIdReturns404() {
        when().delete("http://localhost:8080/employees/1000000")
                .then().assertThat().statusCode(404);
    }

    public static List<Arguments> wrongEmployees() {
        List<Arguments> ans = new ArrayList<>();
        ans.add(Arguments.arguments(1L, new Employee("", "Carpenter", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "Carpenter", "", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "Carpenter", "123-456-789", "")));
        ans.add(Arguments.arguments(1L, new Employee("JohnHasMoreThan25CharactersInItsNameIThink", "Carpenter", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "CarpenterHasMoreThan25CharactersInItsNameIThink", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "Carpenter", "123-456-789HasMoreThan25CharactersInItsNameIThink", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "Carpenter", "123-456-789", "john@carpenterHasMoreThan25CharactersInItsNameIThink.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "Carpenter", "wrongRegex", "john@carpenter.com")));
        ans.add(Arguments.arguments(1L, new Employee("John", "Carpenter", "123-456-789", "wrongRegex@")));
        return ans;
    }
}
