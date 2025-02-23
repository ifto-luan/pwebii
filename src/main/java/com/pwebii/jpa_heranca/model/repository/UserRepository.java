package com.pwebii.jpa_heranca.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;


public interface UserRepository extends JpaRepository<UserImpl, Long> {

    Optional<UserImpl> findByUsername(String username);
    Optional<UserImpl> findByClient(Client client);
    
}
