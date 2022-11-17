package com.example.demo.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.Employee;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ApiTests {

    @Test
    void getSpecificEmployeeReturnsAnEmployee() {
        Employee e = when().get("http://localhost:8080/employees/1")
                .then().assertThat().statusCode(200)
                .extract().body().as(Employee.class);

        Employee expected = new Employee("John", "Doe", "979-490-742", "john@doe.com");
        expected.setId(1);

        assertEquals(expected, e);
    }

    @Test
    void getSpecificEmployeeWithWrongIdReturns404() {
        when().get("http://localhost:8080/employees/1000000")
                .then().assertThat().statusCode(404);
    }

    @Test
    void postEmployeeReturnsCreatedWithAValidLocationHeaderWithTheData() {
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
    void postEmployeeWithWrongValuesReturnsA400(Employee employee) {
        given().contentType(ContentType.JSON).body(employee)
                .when().post("http://localhost:8080/employees")
                .then().assertThat().statusCode(400);
    }

    @Test
    void putEmployeeReturnsA202AndChangesTheValues() {
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
    void putEmployeeWithWrongOrEmptyJSONReturns400(Employee employee) {
        Employee expected = new Employee("John", "Doe", "979-490-742", "john@doe.com");
        String location = given().contentType(ContentType.JSON).body(expected).post("http://localhost:8080/employees")
                .then().extract().header("location");
        expected = given().get(location).then().extract().body().as(Employee.class);

        given().contentType(ContentType.JSON).body(employee).when().put(location)
                .then().assertThat().statusCode(400);

        Employee received = when().get(location)
                .then().extract().as(Employee.class);
        assertEquals(expected, received);
    }

    @Test
    void putEmployeeWithWrongIdReturns404() {
        Employee e = new Employee("John", "Doe", "979-490-742", "john@doe.com");
        given().contentType(ContentType.JSON).body(e).when().put("http://localhost:8080/employees/10000")
                .then().assertThat().statusCode(404);
    }

    @Test
    void deleteEmployeeReturnsA202AndDeletesTheValues() {
        when().delete("http://localhost:8080/employees/1")
                .then().assertThat().statusCode(202);
        when().get("http://localhost:8080/employees/1")
                .then().assertThat().statusCode(404);
    }

    @Test
    void deleteEmployeeWithWrongIdReturns404() {
        when().delete("http://localhost:8080/employees/1000000")
                .then().assertThat().statusCode(404);
    }

    static Stream<Arguments> wrongEmployees() {
        List<Arguments> ans = new ArrayList<>();
        ans.add(Arguments.arguments(new Employee("", "Carpenter", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "Carpenter", "", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "Carpenter", "123-456-789", "")));
        ans.add(Arguments.arguments(new Employee("JohnHasMoreThan25CharactersInItsNameIThink", "Carpenter", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "CarpenterHasMoreThan25CharactersInItsNameIThink", "123-456-789", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "Carpenter", "123-456-789HasMoreThan25CharactersInItsNameIThink", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "Carpenter", "123-456-789", "john@carpenterHasMoreThan25CharactersInItsNameIThink.com")));
        ans.add(Arguments.arguments(new Employee("John", "Carpenter", "wrongRegex", "john@carpenter.com")));
        ans.add(Arguments.arguments(new Employee("John", "Carpenter", "123-456-789", "wrongRegex@")));
        return ans.stream();
    }
}
