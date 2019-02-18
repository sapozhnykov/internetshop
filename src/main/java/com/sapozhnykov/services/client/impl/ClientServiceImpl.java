package com.sapozhnykov.services.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.dao.client.impl.ClientDaoImpl;
import com.sapozhnykov.domain.Client;
import com.sapozhnykov.services.client.ClientService;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public Client createClient(String name, String surname, String phone, String email) {
        return clientDao.add(name, surname, phone, email);
    }

    @Override
    public boolean deleteClientById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }

        return clientDao.deleteById(tempId);
    }

    @Override
    public List<Client> getAllClient() {
        return clientDao.getAll();
    }
}
