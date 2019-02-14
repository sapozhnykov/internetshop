package com.sapozhnykov.view.menu.impl;

import com.sapozhnykov.view.menu.MenuView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class MenuImpl implements MenuView {
    protected final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean isRunningMenu = true;

    public void start() throws IOException {
        makeChoice();
    }

    protected void returnBack() {
        isRunningMenu = false;
    }

    protected void exit() {
        System.exit(0);
    }

    protected abstract void showMenu();

    protected abstract void makeChoice() throws IOException;

    protected void showErrorMessage() {
        System.out.println("ERROR, wrong menu item. Please, try again...");
    }

    protected String inputParameter(String inputName) throws IOException {
        String tempValue = "";

        while(tempValue.equals("")) {
            System.out.println("Input " + inputName + ":");
            tempValue = br.readLine();
        }

        return tempValue;
    }
}
