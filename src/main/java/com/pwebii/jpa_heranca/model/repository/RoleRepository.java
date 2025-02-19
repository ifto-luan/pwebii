package com.pwebii.jpa_heranca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByDescription(String description);
    
}
