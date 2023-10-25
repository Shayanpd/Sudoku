package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class MenuBarComponent {
    private MenuBar menuBar;

    public MenuBarComponent() {
        menuBar = new MenuBar();

        // Create the "File" menu
        Menu fileMenu = new Menu("File");

        // Create items for the "File" menu
        MenuItem loadGameMenuItem = new MenuItem("Load Game");
        MenuItem saveGameMenuItem = new MenuItem("Save Game");
        MenuItem exitMenuItem = new MenuItem("Exit");

        loadGameMenuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Open" is selected
                System.out.println("Open menu item selected");
            }
        });

        saveGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Save" is selected
                System.out.println("Save menu item selected");
            }
        });

        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        // Add items to the "File" menu
        fileMenu.getItems().addAll(loadGameMenuItem, saveGameMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Create the "Game" menu
        Menu gameMenu = new Menu("Game");

        // Create items for the "Game" menu
        Menu newGameMenu = new Menu("New Game");



        MenuItem easyNewGame = new MenuItem("Easy game");
        MenuItem mediumNewGame = new MenuItem("Medium game");
        MenuItem hardNewGame = new MenuItem("Hard game");

        newGameMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Save" is selected
                System.out.println("New Game menu item selected");

            }
        });
        //lägg till svårighetsgradsval

        // Add items to the "Game" menu
        gameMenu.getItems().add(newGameMenu);

        // Add items to the "newGame" menu
        newGameMenu.getItems().add(easyNewGame);
        newGameMenu.getItems().add(mediumNewGame);
        newGameMenu.getItems().add(hardNewGame);


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
        MenuItem clearSpacesMenuItem = new MenuItem("Clear spaces");
        MenuItem checkMenuItem = new MenuItem("Check");
        MenuItem aboutMenuItem = new MenuItem("About");
        MenuItem hintMenuItem = new MenuItem("Hint");

        PopupMenu aboutPopupMenu = new PopupMenu("About");
        aboutPopupMenu.add("hello");

        JFrame aboutPopupMenuJFrame = new JFrame("Sudoku Rules");


        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aboutPopupMenuJFrame.setLayout(new GridLayout(5, 5));
                aboutPopupMenu.addSeparator();
                aboutPopupMenuJFrame.setSize(200, 200);
                aboutPopupMenuJFrame.setVisible(true);
            }

        });

        // Add items to the "Help" menu
        helpMenu.getItems().addAll(clearSpacesMenuItem, checkMenuItem, aboutMenuItem, hintMenuItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

