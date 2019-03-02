package com.sapozhnykov.services.client;

import com.sapozhnykov.domain.Client;

import java.util.List;

public interface ClientService {
    /**
     * Create new Client
     * @param name - name of Client
     * @param surname - surname of Client
     * @param phone - phone number of Client
     * @param email - email of Client
     * @return true if add is successful
     */
    Client createClient(String name, String surname, String phone, String email);

    /**
     * Delete Client by ID
     * @param id - ID of Client
     * @return true if delete is successful
     */
    boolean deleteClientById(String id);

    /**
     * Return all Clients
     * @return all Clients
     */
    List<Client> getAllClient();

    Client getById(String id);

    boolean modifyClient(long clientId, String name, String surname, String phone, String email);

    String getName();
}
