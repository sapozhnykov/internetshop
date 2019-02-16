package com.sapozhnykov.dao.client;

import com.sapozhnykov.domain.Client;

import java.util.ArrayList;

public interface ClientDao {
    /**
     * dd Client to the storage
     * @param client - Client
     * @return true if add is successful
     */
    boolean add(Client client);

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
    ArrayList<Client> getAll();
}
