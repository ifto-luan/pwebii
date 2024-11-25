package com.pwebii.jpa_heranca.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.JuridicalPerson;
import com.pwebii.jpa_heranca.model.entity.NaturalPerson;
import com.pwebii.jpa_heranca.model.entity.Person;

public interface PersonRepository extends JpaRepository <Person, Long> {
    
    List<NaturalPerson> findByNameAndCpf(String name, String cpf);
    List<JuridicalPerson> findByNameAndCnpj(String name, String cnpj);

}
