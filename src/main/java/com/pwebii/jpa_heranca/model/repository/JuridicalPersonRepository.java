package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.JuridicalPerson;

public interface JuridicalPersonRepository extends JpaRepository <JuridicalPerson, Long> {

    List<JuridicalPerson> findByCnpj(String cnpj);
    
}
