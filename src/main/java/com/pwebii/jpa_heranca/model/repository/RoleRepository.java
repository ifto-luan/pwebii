package com.pwebii.jpa_heranca.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByDescription(String description);
    
}
