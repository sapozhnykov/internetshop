package com.sapozhnykov.dao.client;

import com.sapozhnykov.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Add client to storage
     * @param name - name of client
     * @param surname - surname of client
     * @param phone - phone of client
     * @param email - email of client
     * @return
     */
    Client add(String name, String surname, String phone, String email);

    /**
     * Delete Client from the storage by ID
     * @param id - Client ID
     * @return true if delete is successful
     */
    boolean deleteById(long id);

    /**
     * Get all Clients from the storage
     * @return all clients
     */
    List<Client> getAll();
}
