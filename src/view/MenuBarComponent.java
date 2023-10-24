package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        openMenuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Open" is selected
                System.out.println("Open menu item selected");
            }
        });

        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Save" is selected
                System.out.println("Save menu item selected");
            }
        });

        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Exit" is selected
                System.out.println("Exit menu item selected");
            }
        });

        // Add items to the "File" menu
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Create the "Game" menu
        Menu gameMenu = new Menu("Game");

        // Create items for the "Game" menu
        MenuItem newGameMenuItem = new MenuItem("New Game");
        //lägg till svårighetsgradsval

        // Add items to the "Game" menu
        gameMenu.getItems().add(newGameMenuItem);

        // Create the "Help" menu
        Menu helpMenu = new Menu("Help");

        helpMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Exit" is selected
                System.out.println("Help menu item selected");
            }
        });

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

