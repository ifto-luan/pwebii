package com.pwebii.jpa_heranca.model.dto;

import com.pwebii.jpa_heranca.model.entity.Client;
import com.pwebii.jpa_heranca.model.entity.UserImpl;

public class UserClientDTO {

    private Client client;
    private UserImpl user;

    public UserClientDTO() {
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public UserImpl getUser() {
        return user;
    }
    public void setUser(UserImpl user) {
        this.user = user;
    }

    

}
