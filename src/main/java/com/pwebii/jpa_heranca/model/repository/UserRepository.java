package com.pwebii.jpa_heranca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.UserImpl;


public interface UserRepository extends JpaRepository<UserImpl, Long> {

    UserImpl findByUsername(String username);
    
}
