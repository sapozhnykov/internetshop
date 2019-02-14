package com.sapozhnykov.services.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.dao.client.impl.ClientDaoImpl;
import com.sapozhnykov.domain.Client;
import com.sapozhnykov.services.client.ClientService;

import java.util.ArrayList;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public boolean createClient(String name, String surname, String phone, String email) {
        Client client = new Client(name, surname, phone, email);
        boolean result = clientDao.add(client);
        return result;
    }

    @Override
    public boolean deleteClientById(String id) { return clientDao.deleteById(id); }

    @Override
    public ArrayList<Client> getAllClient() {
        return clientDao.getAll();
    }
}
