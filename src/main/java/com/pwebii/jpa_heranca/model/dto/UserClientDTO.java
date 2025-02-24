package com.pwebii.jpa_heranca.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;

import jakarta.validation.constraints.NotBlank;

public class UserClientDTO {

    private Long clientId;
    private Long userId;

    @NotBlank
    private String identifier;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserClientDTO() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    public UserImpl toUser() {

        UserImpl u = new UserImpl();
        u.setId(userId);

        return u;

    }

    public Client toClient() {

        Client c = new Client();
        c.setId(clientId);
        c.setIdentifier(identifier);
        c.setName(name);
        c.setType(type);

        return c;

    }

    public static UserClientDTO fromEntities(UserImpl u, Client c) {

        UserClientDTO dto = new UserClientDTO();
        dto.clientId = c.getId();
        dto.name = c.getName();
        dto.identifier = c.getIdentifier();
        dto.type = c.getType();
        dto.userId = u.getId();

        return dto;

    }

}
