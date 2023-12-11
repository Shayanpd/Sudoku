package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;
import model.SudokuModel;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.SudokuFileIO;

import javax.swing.*;
import java.io.File;


/**
 * The MenuBarComponent class represents the menu bar in the Sudoku game,
 * providing options like loading/saving games, and accessing game features.
 */
public class MenuBarComponent {
    private MenuBar menuBar;
    private Gridview gridview;
    protected String path;
    private JFileChooser fileChooser = new JFileChooser();

    /**
     * Constructor for MenuBarComponent.
     * 
     * @param gridview The Gridview associated with this menu bar.
     */
    public MenuBarComponent(Gridview gridview) {
        menuBar = new MenuBar();
        this.gridview = gridview;


        // Create the "File" menu
        Menu fileMenu = new Menu("File");

        // Create items for the "File" menu
        MenuItem loadGameMenuItem = new MenuItem("Load Game");
        MenuItem saveGameMenuItem = new MenuItem("Save Game");
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem printArray = new MenuItem("Print");

        loadGameMenuItem.setOnAction(event -> {
            try {
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    SudokuModel model = SudokuFileIO.deSerializeFromFile(file); // Ensure this method handles exceptions internally
                    gridview.newModel(model);
                    gridview.getController().setModel(model);
                    gridview.updateViewModel();
                } else {
                    System.out.println("File selection cancelled.");
                }
            } catch (Exception e) {
                System.out.println("Error occurred while loading the game: " + e.getMessage());
                // Show an error dialog to the user
            }
        });


        saveGameMenuItem.setOnAction(event -> {
            System.out.println("Save menu item selected");

            int response = fileChooser.showSaveDialog(null); // Show the save dialog
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    // Ensure sudokuModel is not null before attempting to save
                    if (gridview.getModel() != null) {
                        // Make sure to save in the "savedgames" directory
                        File saveFile = new File("./savedgames/" + file.getName());
                        SudokuFileIO.serializeToFile(saveFile, gridview.getModel());
                    } else {
                        System.out.println("No Sudoku model to save.");
                    }
                } catch (Exception e) {
                    System.out.println("Error occurred while saving the file: " + e.getMessage());
                }
            } else {
                System.out.println("Save operation cancelled.");
            }
        });


        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        printArray.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridview.getModel().printArray();
            }
        });

        // Add items to the "File" menu
        fileMenu.getItems().addAll(loadGameMenuItem, saveGameMenuItem, new SeparatorMenuItem(), exitMenuItem,printArray);

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
                // Define what should happen when "New Easy game" is selected
                SudokuModel model = gridview.getController().createNewEasyModel();
                gridview.newModel(model);
                gridview.getController().setModel(model);
                gridview.updateViewModel();
                System.out.println("New Easy Game menu item selected");
            }
        });
        mediumNewGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "New Medium game" is selected
                SudokuModel model = gridview.getController().createNewMediumModel();
                gridview.newModel(model);
                gridview.getController().setModel(model);
                gridview.updateViewModel();
                System.out.println("New Medium Game menu item selected");
            }
        });
        hardNewGameMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Define what should happen when "New Hard game" is selected
                SudokuModel model = gridview.getController().createNewHardModel();
                gridview.newModel(model);
                gridview.getController().setModel(model);
                gridview.updateViewModel();
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

        // Create items for the "Help" menu
        MenuItem clearSpacesMenuItem = new MenuItem("Clear spaces");
        MenuItem checkMenuItem = new MenuItem("Check");
        MenuItem aboutMenuItem = new MenuItem("About");

        checkMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Define what should happen when "check" is selected
                gridview.getController().compareCurrentGridToSolutionValues();
                gridview.updateViewModel();
                System.out.println("check selected");
            }
        });

        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showAboutPopup();
            }

        });
        clearSpacesMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridview.getController().getModel().clearAllTiles();
                gridview.updateViewModel();
                System.out.println("Clear spaces clicked");
            }
        });

        // Add items to the "Help" menu
        helpMenu.getItems().addAll(clearSpacesMenuItem, checkMenuItem, aboutMenuItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
    }

    /**
     * Shows an informational popup about Sudoku rules.
     */
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

    /**
     * Gets the MenuBar component.
     * 
     * @return The MenuBar instance.
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

}

