package com.sapozhnykov.dao.auth.impl;

import com.sapozhnykov.dao.auth.AuthClientDao;
import com.sapozhnykov.domain.AuthClient;

import java.util.ArrayList;
import java.util.List;

public class AuthClientDaoImpl implements AuthClientDao {
    private static final AuthClientDao AUTH_CLIENT_DAO = new AuthClientDaoImpl();

    private List<AuthClient> authClients = new ArrayList<>();
    private static long tempID = 3;

    private AuthClientDaoImpl() {
        authClients.add(new AuthClient(1, 1, "0501234567", "1234567"));
        authClients.add(new AuthClient(2, 2, "0974321222", "0987654"));
    }

    @Override
    public AuthClient checkSingIn(String phone, String password) {
        for(AuthClient authClient: authClients) {
            if( authClient.getPhone().equals(phone) && authClient.getPassword().equals(password) ) {
                return authClient;
            }
        }
        return null;
    }

    @Override
    public boolean isLoginExists(String phone) {
        boolean result = false;
        for(AuthClient authClient: authClients) {
            if( authClient.getPhone().equals(phone)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean add(long userId, String phone, String password) {
        authClients.add(new AuthClient(tempID++ ,userId, phone, password));
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        boolean result = false;
        for(AuthClient authClient: authClients) {
            if( authClient.getId() == id) {
                authClients.remove(authClient);
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<AuthClient> getAll() {
        return authClients;
    }

    public static AuthClientDao getInstance() {
        return AUTH_CLIENT_DAO;
    }
}
