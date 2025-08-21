package com.br.ayrton.repositories;

import java.util.Optional;

import com.br.ayrton.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

}