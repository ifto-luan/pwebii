package com.pwebii.jpa_heranca.model.dto;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;

import jakarta.validation.constraints.NotBlank;

public class SignupUserClientDTO {

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

    public SignupUserClientDTO() {
    }

    public UserImpl toUser() {

        UserImpl u = new UserImpl();
        u.setUsername(username);
        u.setPassword(password);

        return u;

    }

    public Client toClient() {

        Client c = new Client();
        c.setIdentifier(identifier);
        c.setName(name);
        c.setType(type);

        return c;

    }

    public static SignupUserClientDTO fromEntities(UserImpl u, Client c) {

        SignupUserClientDTO dto = new SignupUserClientDTO();
        dto.name = c.getName();
        dto.identifier = c.getIdentifier();
        dto.type = c.getType();
        dto.username = u.getUsername();
        dto.password = u.getPassword();

        return dto;

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

}
