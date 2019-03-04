package com.sapozhnykov.services.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.dao.client.impl.ClientDBDaoImpl;
import com.sapozhnykov.domain.Client;
import com.sapozhnykov.exceptions.BusinessException;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.services.locator.ServiceLocator;
import com.sapozhnykov.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = ClientDBDaoImpl.getInstance();
    private ValidationService validationService = ServiceLocator.getServiceByName("ValidationService");

    @Override
    public Client createClient(String name, String surname, String phone, String email) {
        Client client = null;
        try{
            validationService.validatePhone(phone);
            validationService.validateEmail(email);
            client = clientDao.add(name, surname, phone, email);
        } catch (BusinessException ex) {
            System.out.println( ex.getMessage() );
        }
        return client;
    }

    @Override
    public boolean deleteClientById(String id) {
        long tempId;

        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }

        return clientDao.deleteById(tempId);
    }

    @Override
    public List<Client> getAllClient() {
        return clientDao.getAll();
    }

    @Override
    public Client getById(String id) {
        long tempId;
        try {
            tempId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
        return clientDao.getById(tempId);
    }

    @Override
    public boolean modifyClient(long clientId, String name, String surname, String phone, String email) {
        boolean result = false;
        try{
            validationService.validatePhone(phone);
            validationService.validateEmail(email);
            result = clientDao.modify(clientId, name, surname, phone, email);
        } catch (BusinessException ex) {
            System.out.println( ex.getMessage() );
        }
        return result;
    }

    @Override
    public String getName() {
        return "ClientService";
    }
}
