package com.sapozhnykov;

import com.sapozhnykov.view.menu.MenuView;
import com.sapozhnykov.view.menu.impl.MainMenuImpl;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MenuView menu = new MainMenuImpl();
        menu.start();
    }
}
