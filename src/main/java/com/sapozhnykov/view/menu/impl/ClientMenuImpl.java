package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

public class ClientMenuImpl extends MenuImpl {
    private final MenuView catalogMenu = new CatalogMenuImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== CLIENT MENU ======");
        System.out.println("______   Options   ______");
        System.out.println("1. Show catalog");
        System.out.println("2. Account settings");
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
                    System.out.println("ACCOUNT SETTINGS");
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
}
