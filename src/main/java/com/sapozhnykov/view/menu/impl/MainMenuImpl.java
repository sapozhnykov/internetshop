package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

import java.io.IOException;

public class MainMenuImpl extends MenuImpl {
    private final MenuView adminMenu = new AdminMenuImpl();
    private final MenuView clientMenu = new ClientMenuImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== MAIN MENU ======");
        System.out.println("______  Options  ______");
        System.out.println("1. Sing in as client");
        System.out.println("2. Sing in as Admin");
        System.out.println("e. Exit");
    }

    @Override
    protected void makeChoice() throws IOException {
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    clientMenu.start();
                    break;
                case "2":
                    adminMenu.start();
                    break;
                case "e":
                    super.exit();
                    break;
                default:
                    super.showErrorMessage();
            }
        }
    }
}
