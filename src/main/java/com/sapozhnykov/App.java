package com.sapozhnykov;

import com.sapozhnykov.view.menu.MenuView;
import com.sapozhnykov.view.menu.impl.MainMenuImpl;

public class App {
    public static void main(String[] args) {
        MenuView mainMenu = new MainMenuImpl();
        mainMenu.start();
    }
}
