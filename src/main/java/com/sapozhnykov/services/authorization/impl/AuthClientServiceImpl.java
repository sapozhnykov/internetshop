package com.sapozhnykov.services.authorization.impl;

import com.sapozhnykov.dao.auth.AuthClientDao;
import com.sapozhnykov.dao.auth.Impl.AuthClientDaoImpl;
import com.sapozhnykov.domain.AuthClient;
import com.sapozhnykov.services.authorization.AuthClientService;

public class AuthClientServiceImpl implements AuthClientService {
    private AuthClientDao authClientDao = new AuthClientDaoImpl();
    private static long currentUserId = -1;

    @Override
    public boolean singIn(String phone, String password) {
        boolean result = false;

        AuthClient authClient = authClientDao.checkSingIn(phone, password);

        if(authClient != null) {
            currentUserId = authClient.getId();
            result = true;
        }

        return result;
    }

    @Override
    public boolean isLoginExists(String phone) {
        return authClientDao.isLoginExists(phone);
    }

    @Override
    public boolean register(long clientId, String phone, String password) {
        boolean result = authClientDao.add(clientId, phone, password);
        if(result) {
            currentUserId = clientId;
        }
        return result;
    }

    public static long getCurrentUserId() {
        return currentUserId;
    }
}
