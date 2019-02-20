package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Client;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.services.client.impl.ClientServiceImpl;

public class ClientListMenuImpl extends MenuImpl{
    private final ClientService clientService = new ClientServiceImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== CLIENT LIST ======");
        showAllClients();
        System.out.println("______   Options   ______");
        System.out.println("1. Add new client");
        System.out.println("2. Delete client");
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

        Client client = clientService.createClient(name, surname, phone, email);
        if(client != null) {
            System.out.println("client saved!");
        } else {
            System.out.println("Error. client don't saved!");
        }
        System.out.println();
    }

    private void deleteClient() {
        String tempId = "";
        boolean result;

        tempId = super.inputParameter("client ID");
        result = clientService.deleteClientById(tempId);
        if(result) {
            System.out.println("client deleted!");
        } else {
            System.out.println("Error. client don't deleted!");
        }
        System.out.println();
    }
}
