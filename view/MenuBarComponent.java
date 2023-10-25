package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.SudokuUtilities;

import javax.swing.*;
import java.io.File;


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
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));

                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                }
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
        MenuItem easyNewGameMenuItem = new MenuItem("Easy game");
        MenuItem mediumNewGameMenuItem = new MenuItem("Medium game");
        MenuItem hardNewGameMenuItem = new MenuItem("Hard game");

        easyNewGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Save" is selected
                System.out.println("New Easy Game menu item selected");
            }
        });
        mediumNewGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Save" is selected
                System.out.println("New Medium Game menu item selected");
            }
        });
        hardNewGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "Save" is selected
                System.out.println("New Hard Game menu item selected");
            }
        });

        // Add items to the "Game" menu
        gameMenu.getItems().add(newGameMenu);

        // Add items to the "newGame" menu
        newGameMenu.getItems().add(easyNewGameMenuItem);
        newGameMenu.getItems().add(mediumNewGameMenuItem);
        newGameMenu.getItems().add(hardNewGameMenuItem);


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

        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAboutPopup();
            }

        });
        clearSpacesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clear spaces clicked");

            }

        });

        // Add items to the "Help" menu
        helpMenu.getItems().addAll(clearSpacesMenuItem, checkMenuItem, aboutMenuItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    private void showAboutPopup() {
        Stage aboutStage = new Stage();
        aboutStage.setTitle("Sudoku Rules");

        Text aboutText = new Text("""
                Sudoku grid consists of 9x9 spaces.
                You can use only numbers from 1 to 9.
                Each 3×3 block can only contain numbers from 1 to 9.
                Each vertical column can only contain numbers from 1 to 9.
                Each horizontal row can only contain numbers from 1 to 9.
                Each number in the 3×3 block, vertical column or horizontal row can be used only once.
                The game is over when the whole Sudoku grid is correctly filled with numbers.""");

        VBox aboutLayout = new VBox();
        aboutLayout.getChildren().add(aboutText);

        Scene scene = new Scene(aboutLayout, 465, 120);
        aboutStage.setScene(scene);

        aboutStage.show();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}

