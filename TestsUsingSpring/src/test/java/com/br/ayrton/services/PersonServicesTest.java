package com.br.ayrton.services;

import com.br.ayrton.exceptions.ResourceNotFoundException;
import com.br.ayrton.model.Person;
import com.br.ayrton.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class PersonServicesTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonServices service;

    private Person person0;

    @BeforeEach
    void setup(){
        person0 = new Person("Ayrton", "Yoshii", "banana@email.com", "Endereço XYZ", "male");
    }

    @Test
    void testGivenPersonObject_WhenSavePerson_ThenReturnPersonObject() {
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person0)).willReturn(person0);

        Person savedPerson = service.create(person0);

        assertNotNull(savedPerson);
        assertEquals("Ayrton", savedPerson.getFirstName());
    }

    @Test
    void testGivenExistingEmail_WhenSavePerson_ThenThrowsException() {
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));

        assertThrows(
                ResourceNotFoundException.class,
                () -> {service.create(person0);},
                () -> "The person with that email already exists: " + person0.getEmail());

        verify(repository, never()).save(any(Person.class));
    }

    @Test
    void testGivenPersonsList_WhenFindAllPersons_ThenReturnPersonsList() {
        Person person1 = new Person("Pedro", "Matias", "pedro@email", "Endereço", "male");
        given(repository.findAll()).willReturn(List.of(person0, person1));

        List<Person> persons = service.findAll();

        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    void testGivenPersonsList_WhenFindAllPersons_ThenReturnEmptyList() {
        given(repository.findAll()).willReturn(Collections.emptyList());

        List<Person> persons = service.findAll();

        assertNotNull(persons);
        assertTrue(persons.isEmpty());
        assertEquals(0, persons.size(), "The list should be empty");
    }

    @Test
    void testGivenPersonsId_WhenFindById_ThenReturnPersonObject() {
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        Person savedPerson = service.findById(1L);

        assertNotNull(savedPerson);
        assertEquals("Ayrton", savedPerson.getFirstName());
    }

    @Test
    void testGivenPersonsObject_WhenUpdatePerson_ThenReturnUpdatedPerson() {
        person0.setId(1L);
        given(repository.save(person0)).willReturn(person0);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        person0.setFirstName("Pedro");
        person0.setLastName("Matias");

        Person updatedPerson = service.update(person0);

        assertNotNull(updatedPerson);
        assertEquals("Pedro", updatedPerson.getFirstName());
        assertEquals("Matias", updatedPerson.getLastName());
    }

    @Test
    void testGivenPersonsObject_WhenDeletePerson_ThenDeletePersonObject() {
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);

        service.delete(1L);

        Person updatedPerson = service.update(person0);

        verify(repository, times(1)).delete(person0);
    }

}