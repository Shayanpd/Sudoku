package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.SudokuModel;
import model.Tile;
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
            System.out.println("SudokuModel or Gridview is null in handleTileClick");
            return; // Or throw an exception
        }
        // Existing logic...
        
        // Implement this method to handle tile clicks.
        // You can update the model and view here.
        if (selectedNumber >= 1 && selectedNumber <= 9 && sudokuModel.getTile(row, col).isEditable()) {
            sudokuModel.setTileValue(row, col, selectedNumber);
            sudokuModel.getTile(row, col).setCorrectValue(true);
            gridview.updateGridTile(row, col, selectedNumber);
                //update sudokumodel
        } 
        else if(selectedNumber == 0 && sudokuModel.getTile(row, col).isEditable())
        {
            sudokuModel.clearTile(row, col);
            gridview.clearTile(row, col); // Implement this method in Gridview
        }
        else
        {
            //throw exception error
        }
        System.out.println("Set" + selectedNumber + " at Row: " + row + " Column: " + col);
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
     * Creates a new Sudoku model with an easy difficulty level.
     * 
     * @return A new SudokuModel with easy difficulty.
     */
    public SudokuModel createNewEasyModel(){
        return new SudokuModel(SudokuUtilities.SudokuLevel.EASY);
    }

    /**
     * Creates a new Sudoku model with a medium difficulty level.
     * 
     * @return A new SudokuModel with medium difficulty.
     */
    public SudokuModel createNewMediumModel(){
        return new SudokuModel(SudokuUtilities.SudokuLevel.MEDIUM);
    }

    /**
     * Creates a new Sudoku model with a hard difficulty level.
     * 
     * @return A new SudokuModel with hard difficulty.
     */
    public SudokuModel createNewHardModel(){
        return new SudokuModel(SudokuUtilities.SudokuLevel.HARD);
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
            gridview.updateGridTile(row, col, correctValue); // Ensure this method correctly updates the UI
        } else {
            // Optionally, handle the case where no hint is available
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
     * Sets the Sudoku model to a new model.
     * 
     * @param sudokuModel The new Sudoku model to set.
     */
    public void setModel(SudokuModel sudokuModel)
    {
        this.sudokuModel = sudokuModel; 
    }
}
