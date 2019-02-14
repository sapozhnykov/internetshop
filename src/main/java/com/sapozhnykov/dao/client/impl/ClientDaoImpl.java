package com.sapozhnykov.dao.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.domain.Client;

import java.util.ArrayList;

public class ClientDaoImpl implements ClientDao {
    private ArrayList<Client> clients = new ArrayList<>();

    public ClientDaoImpl() {
        clients.add(new Client("David" , "Cramer", "0503213232", "david@gmail.com"));
        clients.add(new Client("Mark" , "Bryant", "0630002211", "mark@gmail.com"));
        clients.add(new Client("Kira" , "Nightly", "0672111223", "kira@gmail.com"));
        clients.add(new Client("Max" , "Jameson", "0501234567", "max@gmail.com"));
    }

    @Override
    public boolean add(Client client) {
        return clients.add(client);
    }

    @Override
    public boolean deleteById(String id) {
        long tempId;
        boolean result;
        Client tempClient;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }

        tempClient = findClientById(tempId);
        result = deleteClient(tempClient);
        return result;
    }


    @Override
    public ArrayList<Client> getAll() {
        return this.clients;
    }

    private Client findClientById(long id) {
        for(Client client: clients) {
            if(client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    private boolean deleteClient(Client client) {
        return clients.remove(client);
    }
}
