package com.sapozhnykov.services.authorization.impl;

import com.sapozhnykov.dao.auth.AuthClientDao;
import com.sapozhnykov.dao.auth.impl.AuthClientDBDaoImpl;
import com.sapozhnykov.domain.AuthClient;
import com.sapozhnykov.exceptions.BusinessException;
import com.sapozhnykov.services.authorization.AuthClientService;
import com.sapozhnykov.services.locator.ServiceLocator;
import com.sapozhnykov.validators.ValidationService;

public class AuthClientServiceImpl implements AuthClientService {
    private AuthClientDao authClientDao = AuthClientDBDaoImpl.getInstance();
    private ValidationService validationService = ServiceLocator.getServiceByName("ValidationService");

    private static long currentUserId = -1;

    @Override
    public boolean singIn(String phone, String password) {
        boolean result = false;

        try{
            validationService.validatePhone(phone);
        } catch (BusinessException ex) {
            System.out.println( ex.getMessage() );
        }

        AuthClient authClient = authClientDao.checkSingIn(phone, password);

        if(authClient != null) {
            currentUserId = authClient.getId();
            result = true;
        }

        return result;
    }

    @Override
    public boolean isLoginExists(String phone) {
        boolean result = false;
        try{
            validationService.validatePhone(phone);
            result = authClientDao.isLoginExists(phone);
        } catch (BusinessException ex) {
            System.out.println( ex.getMessage() );
        }
        return result;
    }

    @Override
    public boolean register(long clientId, String phone, String password) {
        boolean result = false;
        try{
            validationService.validatePhone(phone);
            result = authClientDao.add(clientId, phone, password);
        } catch (BusinessException ex) {
            System.out.println( ex.getMessage() );
        }

        if(result) {
            currentUserId = clientId;
        }
        return result;
    }

    public static long getCurrentUserId() {
        return currentUserId;
    }

    @Override
    public String getName() {
        return "AuthClientService";
    }
}
