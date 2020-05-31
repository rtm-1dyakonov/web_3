package com.com.dao;

import com.models.Client;

public interface ClientDAO {
    public void setupClient(Client client);
    public boolean isClientExist(String login, String password);
}
