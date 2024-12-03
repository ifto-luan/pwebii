package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pwebii.jpa_heranca.model.entity.Person;


public interface PersonRepository extends JpaRepository <Person, Long> {

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Person> findAllContainingName(@Param("name") String name);

}
