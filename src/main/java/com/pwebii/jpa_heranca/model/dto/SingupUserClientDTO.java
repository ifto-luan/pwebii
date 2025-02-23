package com.pwebii.jpa_heranca.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;

import jakarta.validation.constraints.NotBlank;

public class SingupUserClientDTO {

    private Long client_id;
    private Long user_id;

    @NotBlank
    private String identifier;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Autowired
    PasswordEncoder passwordEncoder;

    public SingupUserClientDTO() {
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserImpl toUser() {

        UserImpl u = new UserImpl();
        u.setId(user_id);
        u.setUsername(username);
        u.setPassword(password);

        return u;

    }

    public Client toClient() {

        Client c = new Client();
        c.setId(client_id);
        c.setIdentifier(identifier);
        c.setName(name);
        c.setType(type);

        return c;

    }

    public static SingupUserClientDTO fromEntities(UserImpl u, Client c) {

        SingupUserClientDTO dto = new SingupUserClientDTO();
        dto.client_id = c.getId();
        dto.name = c.getName();
        dto.identifier = c.getIdentifier();
        dto.type = c.getType();
        dto.username = u.getUsername();
        dto.password = u.getPassword();

        return dto;

    }

}
