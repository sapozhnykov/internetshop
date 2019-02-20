package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.domain.Client;
import com.sapozhnykov.services.authorization.AuthClientService;
import com.sapozhnykov.services.client.ClientService;
import com.sapozhnykov.view.menu.MenuView;

public class AuthClientMenuImpl extends MenuImpl {
    private final MenuView clientMenu;
    private final AuthClientService authClientService;
    private final ClientService clientService;

    public AuthClientMenuImpl(MenuView clientMenu, AuthClientService authClientService, ClientService clientService) {
        this.clientMenu = clientMenu;
        this.authClientService = authClientService;
        this.clientService = clientService;
    }

    @Override
    protected void showMenu() {
        System.out.println("====== CLIENT AUTHORIZATION ======");
        System.out.println("______       Options        ______");
        System.out.println("1. Sing in");
        System.out.println("2. Registration");
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
                    if( signIn() ) {
                        isWorkContinue = clientMenu.start();
                    }
                    break;
                case "2":
                    if( registration() ) {
                        isWorkContinue = clientMenu.start();
                    }
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

    private boolean signIn() {
        String tempLogin = super.inputParameter("Your login (phone)");
        String tempPassword = super.inputParameter("Your password");
        boolean result = authClientService.singIn(tempLogin, tempPassword);
        if(result) {
            System.out.println("Welcome!");
        } else {
            System.out.println("Sorry, your login or password is incorrect");
        }
        return result;
    }

    private boolean registration() {
        String name = super.inputParameter("Your name");
        String surname = super.inputParameter("Your surname");
        String phone = super.inputParameter("Your phone (it's LOGIN)");
        String password = super.inputParameter("Your password");
        String email = super.inputParameter("Your email");

        boolean tempResult = false;

        if(!authClientService.isLoginExists(phone)) {
            Client client = clientService.createClient(name, surname, phone, email);
            if(client != null) {
                tempResult = authClientService.register(client.getId(), phone, password);
                if(tempResult) {
                    System.out.println(name + " , you have successfully registered!");
                }
            } else {
                System.out.println("Sorry, your data is incorrect");
            }
        } else {
            System.out.println("Sorry, this number of phone already registered");
        }

        return tempResult;
    }
}
