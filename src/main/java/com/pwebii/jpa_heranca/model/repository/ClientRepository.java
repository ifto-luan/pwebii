package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pwebii.jpa_heranca.model.entity.Client;


public interface ClientRepository extends JpaRepository <Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Client> findAllContainingName(@Param("name") String name);

    Client findByIdentifier(String identifier);

}
