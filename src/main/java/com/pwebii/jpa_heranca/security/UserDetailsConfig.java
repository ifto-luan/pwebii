package com.pwebii.jpa_heranca.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; // Use @Service

import com.pwebii.jpa_heranca.model.entity.UserImpl;
import com.pwebii.jpa_heranca.model.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service // Correct annotation
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserImpl user = repository.findByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("usuário não encontrado!");
        }
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
}