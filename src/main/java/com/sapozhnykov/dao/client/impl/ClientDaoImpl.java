package com.sapozhnykov.dao.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static ClientDao clientDao = new ClientDaoImpl();

    private List<Client> clients = new ArrayList<>();
    private static long tempID = 3;

    private ClientDaoImpl() {
        clients.add(new Client(1,"David" , "Cramer", "0503213232", "david@gmail.com"));
        clients.add(new Client(2,"Greg" , "Popovich", "0974321222", "greg@gmail.com"));
    }

    @Override
    public Client add(String name, String surname, String phone, String email) {
        Client client = new Client(tempID, name , surname, phone, email);
        tempID++;
        clients.add(client);
        return client;
    }

    @Override
    public boolean deleteById(long id) {
        boolean result;
        Client tempClient;

        tempClient = getById(id);
        result = deleteClient(tempClient);
        return result;
    }


    @Override
    public List<Client> getAll() {
        return this.clients;
    }

    public Client getById(long id) {
        for(Client client: clients) {
            if(client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean modify(long clientId, String name, String surname, String phone, String email) {
        boolean result = false;
        for(Client client: clients) {
            if(client.getId() == clientId) {
                client.setName(name);
                client.setSurname(surname);
                client.setPhone(phone);
                client.setEmail(email);
                result = true;
            }
        }
        return result;
    }

    private boolean deleteClient(Client client) {
        return clients.remove(client);
    }

    public static ClientDao getInstance() {
        return clientDao;
    }
}
