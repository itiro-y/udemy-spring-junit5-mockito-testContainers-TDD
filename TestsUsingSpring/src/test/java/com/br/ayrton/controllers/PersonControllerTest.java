package com.br.ayrton.controllers;

import com.br.ayrton.exceptions.ResourceNotFoundException;
import com.br.ayrton.model.Person;
import com.br.ayrton.services.PersonServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonServices service;

    private Person person0;

    @BeforeEach
    void setup(){
        person0 = new Person("Ayrton", "Yoshii", "ayoshii@email.com", "Endereço XYZ", "male");
    }

    @Test
    void testGivenPersonObject_WhenCreatePerson_ThenReturnSavedPerson() throws Exception {
        given(service.create(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(
                post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person0)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person0.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person0.getLastName())))
                .andExpect(jsonPath("$.email", is(person0.getEmail())));
    }

    @Test
    void testGivenListOfPersons_WhenFindAllPersons_ThenReturnPersonsList() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person0);
        persons.add(new Person("Pedro", "Matias", "pedro@email", "Endereço T", "male"));
        given(service.findAll()).willReturn(persons);

        ResultActions response = mockMvc.perform(get("/person"));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(persons.size())));
    }

    @Test
    void testGivenPersonsId_WhenFindById_ThenReturnPersonObject() throws Exception {
        long personId = 1L;
        given(service.findById(personId)).willReturn(person0);

        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person0.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person0.getLastName())))
                .andExpect(jsonPath("$.email", is(person0.getEmail())));
    }

    @Test
    void testGivenPersonsId_WhenFindById_ThenThrowAnException() throws Exception {
        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testGivenUpdatedPerson_WhenUpdate_ThenUpdatedPersonObject() throws Exception {
        Person updatedPerson = new Person("Pedro", "Matias", "pedro@email", "Endereço T", "male");

        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(
                put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedPerson)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @Test
    void testGivenInvalidPerson_WhenUpdate_ThenReturnNotFound() throws Exception {
        Person invalidPerson = new Person("", "", "", "", "");

        long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(1));

        ResultActions response = mockMvc.perform(
                put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(invalidPerson)));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testGivenPersonId_WhenDelete_ThenReturnNoContext() throws Exception {
        long personId = 1L;
        willDoNothing().given(service).delete(personId);

        ResultActions response = mockMvc.perform(
                delete("/person/{id}", personId));

        response.andDo(print())
                .andExpect(status().isNoContent());
    }
}