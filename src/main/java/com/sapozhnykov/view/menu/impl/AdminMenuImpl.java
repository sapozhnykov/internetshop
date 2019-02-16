package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

public class AdminMenuImpl extends MenuImpl {
    private final MenuView clientListMenu = new ClientListMenuImpl();
    private final MenuView productListMenu = new ProductListMenuImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== ADMIN MENU ======");
        System.out.println("______   Options  ______");
        System.out.println("1. Show client list");
        System.out.println("2. Show product list");
        System.out.println("r. Return");
        System.out.println("e. Exit");
    }

    @Override
    protected void makeChoice() {
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    clientListMenu.start();
                    break;
                case "2":
                    productListMenu.start();
                    break;
                case "r":
                    super.returnBack();
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
