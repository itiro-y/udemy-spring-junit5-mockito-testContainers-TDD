package com.br.ayrton.repositories;

import java.util.Optional;

import com.br.ayrton.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    @Query("select p from Person p where p.firstName =?1 and p.lastName = ?2")
    Person findByJPQL(String firstName, String lastName);

    @Query("select p from Person p where p.firstName = :firstName and p.lastName = :lastName")
    Person findByJPQLNamedParameter(@Param("firstName") String firstName, @Param("lastName")String lastName);

    @Query(value = "SELECT * FROM person p WHERE p.first_name = :firstName AND p.last_name = :lastName", nativeQuery = true)
    Person findByNativeSQL(@Param("firstName") String firstName, @Param("lastName")String lastName);

}