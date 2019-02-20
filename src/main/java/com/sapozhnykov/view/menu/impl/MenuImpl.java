package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

import java.util.Scanner;

public abstract class MenuImpl implements MenuView {
    protected final Scanner scanner = new Scanner(System.in);
    protected boolean isRunningMenu = true;

    public boolean start(){
        isRunningMenu = true;
        return makeChoice();
    }

    protected void returnBack() {
        isRunningMenu = false;
    }

    protected abstract void showMenu();

    protected abstract boolean makeChoice();

    protected void showErrorMessage() {
        System.out.println("ERROR, wrong menu item. Please, try again...");
    }

    protected String inputParameter(String inputName) {
        String tempValue = "";

        while(tempValue.equals("")) {
            System.out.println("Input " + inputName + ":");
            tempValue = scanner.nextLine();
        }

        return tempValue;
    }
}
