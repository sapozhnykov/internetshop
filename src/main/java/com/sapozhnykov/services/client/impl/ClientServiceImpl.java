package com.sapozhnykov.services.client.impl;

import com.sapozhnykov.dao.client.ClientDao;
import com.sapozhnykov.dao.client.impl.ClientDaoImpl;
import com.sapozhnykov.domain.Client;
import com.sapozhnykov.exceptions.BusinessException;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.validators.ValidationService;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

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
}
