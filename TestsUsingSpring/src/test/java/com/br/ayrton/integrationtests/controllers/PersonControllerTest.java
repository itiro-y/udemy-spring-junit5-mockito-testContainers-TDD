package com.br.ayrton.integrationtests.controllers;

import com.br.ayrton.config.TestConfigs;
import com.br.ayrton.integrationtests.testcontainers.AbstractIntegrationTest;
import com.br.ayrton.model.Person;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static Person person;

    @BeforeAll
    public static void setup(){
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person("Ayrton", "Yoshii",
                "ayoshii@email.com", "Endereço XYZ", "male");
    }

    @DisplayName("JUnit test for Given create person object When create one person Then return a person object")
    @Order(1)
    @Test
    void integrationTest_GivenPersonObject_WhenCreateOnePerson_ShouldReturnAPersonObject() throws IOException {
        var content = given()
                .spec(specification) // Given
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when().post() // When
                .then().statusCode(200)  // Then
                .extract().body().asString();

        Person createdPerson = mapper.readValue(content, Person.class);

        person = createdPerson; // Assign the created person to the static variable for later tests, since this is the first test!

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getEmail());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Ayrton", createdPerson.getFirstName());
        assertEquals("Yoshii", createdPerson.getLastName());
        assertEquals("Endereço XYZ", createdPerson.getAddress());
        assertEquals("ayoshii@email.com", createdPerson.getEmail());
        assertEquals("male", createdPerson.getGender());
    }

    @DisplayName("JUnit test for Given create person object When updating one person Should return an updated person object")
    @Order(2)
    @Test
    void integrationTest_GivenPersonObject_WhenUpdateOnePerson_ShouldReturnAnUpdatedPersonObject() throws IOException {
        person.setFirstName("Marcio");
        person.setLastName("Lima");

        var content = given()
                .spec(specification) // Given
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when().put() // When
                .then().statusCode(200)  // Then
                .extract().body().asString();

        Person updatedPerson = mapper.readValue(content, Person.class);

        person = updatedPerson; // Assign the created person to the static variable for later tests, since this is the first test!

        assertNotNull(updatedPerson);
        assertNotNull(updatedPerson.getId());
        assertNotNull(updatedPerson.getFirstName());
        assertNotNull(updatedPerson.getLastName());
        assertNotNull(updatedPerson.getAddress());
        assertNotNull(updatedPerson.getEmail());
        assertNotNull(updatedPerson.getGender());

        assertTrue(updatedPerson.getId() > 0);
        assertEquals("Marcio", updatedPerson.getFirstName());
        assertEquals("Lima", updatedPerson.getLastName());
        assertEquals("Endereço XYZ", updatedPerson.getAddress());
        assertEquals("ayoshii@email.com", updatedPerson.getEmail());
        assertEquals("male", updatedPerson.getGender());
    }

    @DisplayName("JUnit test for Given person ID When finding by ID Should return a person object with same ID")
    @Order(3)
    @Test
    void integrationTest_GivenPersonID_WhenFindingById_ShouldAPersonObjectWithSameId() throws IOException {
        var content = given()
                .spec(specification) // Given
                .pathParam("id", person.getId())
                .when().get("{id}") // When
                .then().statusCode(200)  // Then
                .extract().body().asString();

        Person foundPerson = mapper.readValue(content, Person.class);

        assertNotNull(foundPerson);
        assertNotNull(foundPerson.getId());
        assertNotNull(foundPerson.getFirstName());
        assertNotNull(foundPerson.getLastName());
        assertNotNull(foundPerson.getAddress());
        assertNotNull(foundPerson.getEmail());
        assertNotNull(foundPerson.getGender());

        assertTrue(foundPerson.getId() > 0);
        assertEquals("Marcio", foundPerson.getFirstName());
        assertEquals("Lima", foundPerson.getLastName());
        assertEquals("Endereço XYZ", foundPerson.getAddress());
        assertEquals("ayoshii@email.com", foundPerson.getEmail());
        assertEquals("male", foundPerson.getGender());
    }

    @DisplayName("JUnit test for When deleting a person Should NoContent")
    @Order(5)
    @Test
    void integrationTest_WhenDelete_ShouldReturnNoContent() throws IOException {
        given().spec(specification) // Given
                .pathParam("id", person.getId())
                .when().delete("{id}") // When
                .then().statusCode(204);  // Then

    }
}