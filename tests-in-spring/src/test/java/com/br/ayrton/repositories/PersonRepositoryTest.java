package com.br.ayrton.repositories;

import com.br.ayrton.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false;MODE=MYSQL",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"

})
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Given Person Object When Saved Then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSaved_thenReturnSavedPerson(){
        Person person0 = new Person("Ayrton", "Yoshii", "ayoshii@email.com", "Endereço XYZ", "male");

        Person savedPerson = personRepository.save(person0);

        assertNotNull(savedPerson, () -> "Saved person should not be null");
        assertTrue(savedPerson.getId() > 0, () -> "Saved person's ID should exist and be greater than 0");
    }

    @DisplayName("Given Person Object When Find All Is Invoked Then Return a List of Person")
    @Test
    void testGivenPersonObject_whenFindAll_thenReturnPersonList(){
        Person person0 = new Person("Ayrton", "Yoshii", "ayoshii@email.com", "Endereço XYZ", "male");
        Person person1 = new Person("Pedro", "Matias", "ayoshii@email.com", "Endereço XYZ", "male");
        personRepository.save(person0);
        personRepository.save(person1);

        List<Person> personList = personRepository.findAll();

        assertNotNull(personList, () -> "Person list should not be null");
        for(Person person : personList){
            System.out.println(person.getFirstName());
        }
        assertEquals(personList.size(), 2, () -> "Person list should contain 2 elements");
    }

    @DisplayName("Given Person Object When findById Then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnPersonObject(){
        Person person0 = new Person("ASD", "DSA", "ayoshii@email.com", "Endereço XYZ", "male");

        personRepository.save(person0);

        Person foundPerson = personRepository.findById(person0.getId()).get();

        assertNotNull(foundPerson, () -> "Found person should not be null");
        assertEquals(foundPerson.getId(), person0.getId(), () -> "Found person ID should match the saved person's ID");
    }

    @DisplayName("Given Person Object When findByEmail Then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject(){
        Person person0 = new Person("ABC", "DASD", "ayoshii@email.com", "Endereço XYZ", "male");

        personRepository.save(person0);

        Person foundPerson = personRepository.findByEmail(person0.getEmail()).get();

        assertNotNull(foundPerson, () -> "Found person should not be null");
        assertEquals(foundPerson.getEmail(), person0.getEmail(), () -> "Found person ID should match the saved person's ID");
    }


}