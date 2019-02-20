package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Client;
import com.sapozhnykov.services.authorization.impl.AuthClientServiceImpl;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.view.menu.MenuView;

public class ClientMenuImpl extends MenuImpl {
    private final MenuView catalogMenu;
    private final ClientService clientService;

    public ClientMenuImpl(MenuView catalogMenu, ClientService clientService) {
        this.catalogMenu = catalogMenu;
        this.clientService = clientService;
    }

    @Override
    protected void showMenu() {
        System.out.println("====== CLIENT MENU ======");
        System.out.println("______   Options   ______");
        System.out.println("1. Show catalog");
        System.out.println("2. Modify my account");
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
                    isWorkContinue = catalogMenu.start();
                    break;
                case "2":
                    modifyMyAccount();
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

    private void modifyMyAccount() {
        long userId = AuthClientServiceImpl.getCurrentUserId();
        Client client = clientService.getById(userId+"");
        System.out.println("===== User information =====");
        System.out.println(client);

        String name = super.inputParameter("name");
        String surname = super.inputParameter("surname");
        String phone = super.inputParameter("phone");
        String email = super.inputParameter("email");

        boolean result = clientService.modifyClient(userId, name, surname, phone, email);

        if(result) {
            System.out.println("Modified!");
        } else {
            System.out.println("Error. Not modified!");
        }
        System.out.println();
    }
}
