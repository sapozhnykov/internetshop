package com.sapozhnykov.dao.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private List<Client> clients = new ArrayList<>();
    private static long tempID = 1;

    public ClientDaoImpl() {
        clients.add(new Client(tempID++,"David" , "Cramer", "0503213232", "david@gmail.com"));
        clients.add(new Client(tempID++,"Greg" , "Popovich", "0974321222", "greg@gmail.com"));
    }

    @Override
    public Client add(String name, String surname, String phone, String email) {
        Client client = new Client(tempID++, name , surname, phone, email);
        clients.add(client);
        return client;
    }

    @Override
    public boolean deleteById(long id) {
        boolean result;
        Client tempClient;

        tempClient = findClientById(id);
        result = deleteClient(tempClient);
        return result;
    }


    @Override
    public List<Client> getAll() {
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
