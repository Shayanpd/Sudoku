package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuBarComponent {
    private MenuBar menuBar;

    public MenuBarComponent() {
        menuBar = new MenuBar();

        // Create the "File" menu
        Menu fileMenu = new Menu("File");

        // Create items for the "File" menu
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");

        // Add items to the "File" menu
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Create the "Game" menu
        Menu gameMenu = new Menu("Game");

        // Create items for the "Game" menu
        MenuItem newGameMenuItem = new MenuItem("New Game");

        // Add items to the "Game" menu
        gameMenu.getItems().add(newGameMenuItem);

        // Create the "Help" menu
        Menu helpMenu = new Menu("Help");

        // Create items for the "Help" menu
        MenuItem aboutMenuItem = new MenuItem("About");

        // Add items to the "Help" menu
        helpMenu.getItems().add(aboutMenuItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

