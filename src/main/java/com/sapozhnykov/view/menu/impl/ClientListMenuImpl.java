package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Client;
import com.sapozhnykov.services.authorization.AuthClientService;
import com.sapozhnykov.services.client.ClientService;

public class ClientListMenuImpl extends MenuImpl{
    private final ClientService clientService;
    private final AuthClientService authClientService;

    public ClientListMenuImpl(ClientService clientService, AuthClientService authClientService) {
        this.clientService = clientService;
        this.authClientService = authClientService;
    }

    @Override
    protected void showMenu() {
        System.out.println("====== CLIENT LIST ======");
        showAllClients();
        System.out.println("______   Options   ______");
        System.out.println("1. Add new client");
        System.out.println("2. Delete client");
        System.out.println("3. Modify client");
        System.out.println("r. Return");
        System.out.println("e. Exit");
    }

    @Override
    protected boolean makeChoice() {
        boolean isWorkContinue = true;
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    addClient();
                    break;
                case "2":
                    deleteClient();
                    break;
                case "3":
                    modifyClient();
                    break;
                case "r":
                    super.returnBack();
                    break;
                case "e":
                    isWorkContinue = false;
                    super.returnBack();
                    break;
                default:
                    super.showErrorMessage();
            }
            if(!isWorkContinue) {
                super.returnBack();
            }
        }
        return isWorkContinue;
    }

    private void showAllClients() {
        clientService.getAllClient().forEach(System.out::println);
    }

    private void addClient() {
        String name = "";
        String surname = "";
        String phone = "";
        String email = "";

        name = super.inputParameter("name");
        surname = super.inputParameter("surname");
        phone = super.inputParameter("phone");
        email = super.inputParameter("email");

        if(!authClientService.isLoginExists(phone)) {
            Client client = clientService.createClient(name, surname, phone, email);
            if(client != null) {
                System.out.println("Client saved!");
            } else {
                System.out.println("Error. client don't saved!");
            }
            System.out.println();
        } else {
            System.out.println("Sorry, this number of phone already registered");
        }

    }

    private void deleteClient() {
        String tempId = "";
        boolean result;

        tempId = super.inputParameter("client ID");
        result = clientService.deleteClientById(tempId);
        if(result) {
            System.out.println("Client deleted!");
        } else {
            System.out.println("Error. client don't deleted!");
        }
        System.out.println();
    }

    private void modifyClient() {
        String tempId = "";
        long clientId;
        String name;
        String surname;
        String phone;
        String email;

        boolean result;

        tempId = super.inputParameter("client ID");
        Client client = clientService.getById(tempId);
        if(client == null) {
            System.out.println("Client not found");
            return;
        }

        System.out.println("====== Client information: ======");
        System.out.println(client);

        clientId = client.getId();
        name = super.inputParameter("name");
        surname = super.inputParameter("surname");
        phone = super.inputParameter("phone");
        email = super.inputParameter("email");

        result = clientService.modifyClient(clientId, name, surname, phone, email);

        if(result) {
            System.out.println("Client modified!");
        } else {
            System.out.println("Error. client don't modified!");
        }
        System.out.println();
    }
}
