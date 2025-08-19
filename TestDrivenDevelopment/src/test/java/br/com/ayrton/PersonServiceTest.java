package br.com.ayrton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Keith", "Moon", "Kmoon@email.com", "London - UK", "Male");
    }

    @DisplayName("When Creating a Person with Valid Data, Should Return the Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject(){
        // Arrange
        IPersonService service = new PersonService();

        // Act
        Person actual = service.createPerson(person);

        // Assert
        assertNotNull(actual, () -> "The person object should not be null!");
    }

    @DisplayName("When Creating a Person with Valid Data, Should Return the Person Object's Valid Fields")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainValidFieldsInReturnedPersonObject(){
        // Arrange
        IPersonService service = new PersonService();

        // Act
        Person actual = service.createPerson(person);

        // Assert
        assertNotNull(person.getId(), () -> "The person object should not be null!");
        assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The first name should be " + person.getFirstName() + " but was " + actual.getFirstName());
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The last name should be " + person.getLastName() + " but was " + actual.getLastName());
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The email should be " + person.getEmail() + " but was " + actual.getEmail());
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The address should be " + person.getAddress() + " but was " + actual.getAddress());
        assertEquals(person.getGender(), actual.getGender (), () -> "The gender should be " + person.getGender() + " but was " + actual.getGender());
    }

    @DisplayName("When Creating a Person with Null First Name, Should Throw IllegalArgumentException")
    @Test
    void testCreatePerson_WithNullEmail_ShouldThrowIllegalArgumentException(){
        // Arrange
        IPersonService service = new PersonService();

        // Act
        person.setEmail(null);

        String expectedMessage = "Email is null or empty";

        // Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {service.createPerson(person);},
                () -> "Expected IllegalArgumentException to be thrown" );

        assertEquals(expectedMessage, exception.getMessage());
    }
}
