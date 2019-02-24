package com.sapozhnykov.services.locator;

import com.sapozhnykov.view.menu.MenuView;

public interface LocatorService {
    /**
     * This method create instances and return Main Menu
     * The main menu is the starting point of the program.
     * @return Main menu
     */
    MenuView start();
}
