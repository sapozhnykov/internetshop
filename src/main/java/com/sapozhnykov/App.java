package com.sapozhnykov;

import com.sapozhnykov.services.locator.LocatorService;
import com.sapozhnykov.services.locator.impl.LocatorServiceImpl;
import com.sapozhnykov.view.menu.MenuView;

public class App {
    public static void main(String[] args) {
        LocatorService locatorService = new LocatorServiceImpl();
        MenuView mainMenu = locatorService.start();
        mainMenu.start();
    }
}
