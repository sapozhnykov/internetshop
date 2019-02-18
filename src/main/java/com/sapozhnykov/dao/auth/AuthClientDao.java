package com.sapozhnykov.dao.auth;

import com.sapozhnykov.domain.AuthClient;

import java.util.List;

public interface AuthClientDao {

    /**
     * Add registered user
     * @param userId - ID of user
     * @param phone - phone/login of user
     * @param password - password of user
     * @return true if add is successful
     */
    boolean add(long userId, String phone, String password);

    /**
     * Delete AuthClient from the storage by ID
     * @param id - AuthClient ID
     * @return true if delete is successful
     */
    boolean deleteById(long id);

    /**
     * Get all AuthClients from the storage
     * @return all authorized clients
     */
    List<AuthClient> getAll();

    AuthClient checkSingIn(String phone, String password);

    boolean isLoginExists(String phone);
}
