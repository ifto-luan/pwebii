package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.NaturalPerson;

public interface NaturalPersonRepository extends JpaRepository <NaturalPerson, Long> {

    List<NaturalPerson> findByCpf(String cpf);
    
}
