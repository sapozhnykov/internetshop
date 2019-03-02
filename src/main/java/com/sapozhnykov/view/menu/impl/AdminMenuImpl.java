package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

public class AdminMenuImpl extends MenuImpl {
    private final MenuView clientListMenu = new ClientListMenuImpl();
    private final MenuView productListMenu = new ProductListMenuImpl();
    private final MenuView orderListMenu = new OrderListMenuImpl();

    @Override
    protected void showMenu() {
        System.out.println("====== ADMIN MENU ======");
        System.out.println("______   Options  ______");
        System.out.println("1. Show client list");
        System.out.println("2. Show product list");
        System.out.println("3. Show order list");
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
                    isWorkContinue = clientListMenu.start();
                    break;
                case "2":
                    isWorkContinue = productListMenu.start();
                    break;
                case "3":
                    isWorkContinue = orderListMenu.start();
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
