package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.SudokuModel;
import model.Tile;
import util.SudokuFileIO;
import util.SudokuUtilities;
import view.Gridview;

import static util.SudokuUtilities.GRID_SIZE;

/**
 * The controller class for a Sudoku game, responsible for managing user interactions and updating the model and view.
 */
public class SudokuController {
    private SudokuModel sudokuModel;
    private final Gridview gridview;
    private final FileChooser fileChooser;
    public static int selectedNumber;

    /**
     * Constructor for SudokuController.
     * 
     * @param sudokuModel The Sudoku model to control.
     * @param gridview The grid view to update based on the model.
     */
    public SudokuController(SudokuModel sudokuModel, Gridview gridview) {
        this.sudokuModel = sudokuModel;
        this.gridview = gridview;
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Open A Sudoku File");
        this.fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Sudoku Files", "*.sudoku" ));
    }

    /**
     * Handles a click on a tile in the Sudoku grid.
     * 
     * @param row The row index of the clicked tile.
     * @param col The column index of the clicked tile.
     */
    public void handleTileClick(int row, int col) {
        if (sudokuModel == null || gridview == null) {
            throw new RuntimeException("SudokuModel or Gridview is null in handleTileClick");
        }
    
        if (selectedNumber >= 1 && selectedNumber <= 9 && sudokuModel.getTile(row, col).isEditable()) {
            sudokuModel.setTileValue(row, col, selectedNumber);
            sudokuModel.getTile(row, col).setCorrectValue(true);
            gridview.updateGridTile(row, col, selectedNumber);
        } else if (selectedNumber == 0 && sudokuModel.getTile(row, col).isEditable()) {
            sudokuModel.clearTile(row, col);
            gridview.clearTile(row, col);
        } else {
            throw new RuntimeException("Invalid tile selection or tile not editable");
        }
        System.out.println("Set " + selectedNumber + " at Row: " + row + " Column: " + col);
    }
    
    
    /**
     * Displays a game over screen when the puzzle is solved.
     */
    public void gameOverScreen() {
        // Create an alert with a specific type
        Alert alert = new Alert(AlertType.INFORMATION);

        // Set the title and header text
        alert.setTitle("Game Over");
        alert.setHeaderText(null); // You can set this to a string if you want a header

        // Set the content text
        alert.setContentText("Game over, You Win!");

        // Show the alert and wait for it to be closed
        alert.showAndWait();
    }

    /**
     * Handles the change in the selected number for tile placement.
     * 
     * @param selectedNumber The newly selected number.
     */
    public void handleSelectedNumberChange(int selectedNumber) {
        // Implement this method to handle changes in the selected number.
        SudokuModel.setSelectedNumber(selectedNumber);
    }

    /**
     * Creates a new Sudoku model with the given difficulty level.
     * 
     * @return A new SudokuModel with the given difficulty.
     */
    public SudokuModel createNewModel(SudokuUtilities.SudokuLevel level){
        return new SudokuModel(level);
    }

    /**
     * Provides a hint by setting one incorrect tile to the correct value.
     */
    
    /**
     * Provides a hint by setting one incorrect tile to the correct value.
     */
    public void handleHint() {
        int[] hint = sudokuModel.provideHint();
        if (hint != null) {
            int row = hint[0];
            int col = hint[1];
            int correctValue = hint[2];
    
            sudokuModel.setTileValue(row, col, correctValue);
            sudokuModel.getTile(row,col).setCorrectValue(true);
            gridview.updateGridTile(row, col, correctValue);
        } else {
            gameOverScreen();
        }
    }
    
    

    /**
     * Compares the current grid to the solution and updates the correctness of each tile.
     */
    public void compareCurrentGridToSolutionValues() {
        Tile[][] solutionValues = sudokuModel.getSolutionValues();
        Tile[][] currentGrid = sudokuModel.getGrid();
        int isSolved = 0;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (currentGrid[row][col].getValue() != solutionValues[row][col].getValue() && currentGrid[row][col].getValue() != 0) {
                    currentGrid[row][col].setCorrectValue(false);
                } else {
                    if (currentGrid[row][col].getValue() != 0) 
                    {
                        currentGrid[row][col].setCorrectValue(true);
                        isSolved++;
                    }
                }
            }
        }

        if (isSolved == GRID_SIZE * GRID_SIZE) {
            gameOverScreen();
        }
    }
    /**
     * Gets the current Sudoku model.
     * 
     * @return The current SudokuModel.
     */
    public SudokuModel getModel()
    {
        return sudokuModel;
    }

/**
 * Loads in a saved sudoku game.
 */
    public void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Sudoku Game File");
        File file = fileChooser.showOpenDialog(null); // Adjust as necessary for JavaFX stage
        if (file != null) {
            try {
                // Deserialize the Sudoku model from the file
                SudokuModel model = SudokuFileIO.deSerializeFromFile(file);
                setModel(model);
                gridview.updateViewModel();
            } catch (Exception e) {
                // Handle exceptions
                System.out.println("Error occurred while loading the game: " + e.getMessage());
            }
        }
    }
    

/**
 * Saves the current game to a file.
 */
public void saveGame() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Sudoku Game File");
    File file = fileChooser.showSaveDialog(null); // This requires a JavaFX stage, adjust as necessary
    if (file != null) {
        try {
            SudokuFileIO.serializeToFile(file, sudokuModel);
        } catch (Exception e) {
            // Handle exceptions
            System.out.println("Error occurred while saving the game: " + e.getMessage());
        }
    }
}
    /**
     * Sets the Sudoku model to a new model.
     * 
     * @param sudokuModel The new Sudoku model to set.
     */
    public void setModel(SudokuModel sudokuModel)
    {
        this.sudokuModel = sudokuModel; 
    }
}
