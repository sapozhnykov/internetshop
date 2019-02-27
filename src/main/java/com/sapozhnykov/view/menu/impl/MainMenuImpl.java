package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

public class MainMenuImpl extends MenuImpl {
    private final MenuView adminMenu = new AdminMenuImpl();
    private final MenuView authClientMenu = new AuthClientMenuImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== MAIN MENU ======");
        System.out.println("______  Options  ______");
        System.out.println("1. I'm a Client");
        System.out.println("2. I'm the Admin");
        System.out.println("e. Exit");
    }

    @Override
    protected boolean makeChoice() {
        boolean isWorkContinue = true;
        while (super.isRunningMenu) {
            showMenu();
            switch (super.inputParameter("number of menu")) {
                case "1":
                    isWorkContinue = authClientMenu.start();
                    break;
                case "2":
                    isWorkContinue = adminMenu.start();
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
