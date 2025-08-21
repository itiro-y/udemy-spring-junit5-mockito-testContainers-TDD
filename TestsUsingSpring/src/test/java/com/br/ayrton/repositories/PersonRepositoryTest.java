package com.br.ayrton.repositories;

import com.br.ayrton.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    private Person person0;

    @BeforeEach
    void setup(){
        person0 = new Person("Ayrton", "Yoshii", "ayoshii@email.com", "Endereço XYZ", "male");
    }

    @DisplayName("Given Person Object When Saved Then Return Saved Person")
    @Test
    void testGivenPersonObject_whenSaved_thenReturnSavedPerson(){

        Person savedPerson = personRepository.save(person0);

        assertNotNull(savedPerson, () -> "Saved person should not be null");
        assertTrue(savedPerson.getId() > 0, () -> "Saved person's ID should exist and be greater than 0");
    }

    @DisplayName("Given Person Object When Find All Is Invoked Then Return a List of Person")
    @Test
    void testGivenPersonObject_whenFindAll_thenReturnPersonList(){
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
        personRepository.save(person0);

        Person foundPerson = personRepository.findById(person0.getId()).get();

        assertNotNull(foundPerson, () -> "Found person should not be null");
        assertEquals(foundPerson.getId(), person0.getId(), () -> "Found person ID should match the saved person's ID");
    }

    @DisplayName("Given Person Object When findByEmail Then Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnPersonObject(){
        personRepository.save(person0);

        Person foundPerson = personRepository.findByEmail(person0.getEmail()).get();

        assertNotNull(foundPerson, () -> "Found person should not be null");
        assertEquals(foundPerson.getEmail(), person0.getEmail(), () -> "Found person email should match the saved person's email");
    }

    @DisplayName("Given Person Object When updatePerson Then Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnUpdatedPersonObject(){
        personRepository.save(person0);

        Person savedPerson = personRepository.findById(person0.getId()).get();
        savedPerson.setFirstName("Updated Name");
        savedPerson.setLastName("Updated Last Name");

        Person updatedPerson = personRepository.save(savedPerson);

        assertNotNull(updatedPerson, () -> "Found person should not be null");
        assertEquals("Updated Name", updatedPerson.getFirstName(), () -> "Updated person's first name should match the saved person's first name");
        assertEquals("Updated Last Name", updatedPerson.getLastName(), () -> "Updated person's last name should match the saved person's last name");
    }

    @DisplayName("Given Person Object When deletePerson Then Delete Person")
    @Test
    void testGivenPersonObject_whenDelete_thenRemovePerson(){
        personRepository.save(person0);

        personRepository.deleteById(person0.getId());
        Optional<Person> foundPerson = personRepository.findById(person0.getId());

        assertTrue(foundPerson.isEmpty(), () -> "Found person should not be null");
    }

    @DisplayName("Given firstName and lastName When findByJPQL Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByJPQL_thenReturnPersonObject(){
        Person person0 = new Person("ASD", "DSA", "ayoshii@email.com", "Endereço XYZ", "male");

        personRepository.save(person0);
        Person foundPerson = personRepository.findByJPQL("ASD", "DSA");

        assertEquals(person0, foundPerson, () -> "Found person should match the saved person");
    }

    @DisplayName("Given firstName and lastName When findByJPQLNamedParameters Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByJPQLNamedParameters_thenReturnPersonObject(){
        Person person0 = new Person("ASD", "DSA", "ayoshii@email.com", "Endereço XYZ", "male");

        personRepository.save(person0);
        Person foundPerson = personRepository.findByJPQLNamedParameter("ASD", "DSA");

        assertEquals(person0, foundPerson, () -> "Found person should match the saved person");
    }

    @DisplayName("Given firstName and lastName When findByNativeSQL Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByJNativeSQL_thenReturnPersonObject(){
        Person person0 = new Person("ASD", "DSA", "ayoshii@email.com", "Endereço XYZ", "male");

        personRepository.save(person0);
        Person foundPerson = personRepository.findByNativeSQL("ASD", "DSA");

        assertEquals(person0, foundPerson, () -> "Found person should match the saved person");
    }
}