package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
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
     * Compares the current grid to the solution and updates the correctness of each tile.
     */
    public void compareCurrentGridToSolutionValues(){
        Tile[][] solutionValues = sudokuModel.getSolutionValues();
        Tile[][] currentGrid = sudokuModel.getGrid();
        int gameover = 0;
        for (int row = 0; row < GRID_SIZE; row++){
            for (int col = 0; col < GRID_SIZE; col++){
                if (currentGrid[row][col].getValue() != 0 && currentGrid[row][col].getValue() != solutionValues[row][col].getValue()) {
                    currentGrid[row][col].setCorrectValue(false);
                }
                else 
                {
                    currentGrid[row][col].setCorrectValue(true);
                    if (currentGrid[row][col].getValue() != 0) gameover++;
                }
            }
        }
        if (gameover == GRID_SIZE * GRID_SIZE) gameOverScreen();
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
        int rand = (int)(Math.random() * 2) + 1;
        if (rand == 1) return new SudokuModel(SudokuUtilities.SudokuLevel.EASY);
        else return new SudokuModel(SudokuUtilities.SudokuLevel.EASY_REVERSE);
    }

    /**
     * Creates a new Sudoku model with a medium difficulty level.
     * 
     * @return A new SudokuModel with medium difficulty.
     */
    public SudokuModel createNewMediumModel(){
        int rand = (int)(Math.random() * 2) + 1;
        if (rand == 1) return new SudokuModel(SudokuUtilities.SudokuLevel.MEDIUM);
        else return new SudokuModel(SudokuUtilities.SudokuLevel.MEDIUM_REVERSE);
    }

    /**
     * Creates a new Sudoku model with a hard difficulty level.
     * 
     * @return A new SudokuModel with hard difficulty.
     */
    public SudokuModel createNewHardModel(){
        int rand = (int)(Math.random() * 2) + 1;
        if (rand == 1) return new SudokuModel(SudokuUtilities.SudokuLevel.HARD);
        else return new SudokuModel(SudokuUtilities.SudokuLevel.HARD_REVERSE);
    }

    /**
     * Provides a hint by setting one incorrect tile to the correct value.
     */
    public void handleHint()
    {
        Tile[][] solutionValues = sudokuModel.getSolutionValues();
        Tile[][] currentGrid = sudokuModel.getGrid();
        int[][] hintGrid;
        int GRID_SIZE_SQUARED = GRID_SIZE * GRID_SIZE;
        hintGrid = new int[GRID_SIZE_SQUARED][3];
        int counter = 0;
        for (int row = 0; row < GRID_SIZE; row++){
            for (int col = 0; col < GRID_SIZE; col++){
                if (solutionValues[row][col].getValue() != currentGrid[row][col].getValue() && currentGrid[row][col].isEditable()){
                    hintGrid[counter][0] = solutionValues[row][col].getValue();
                    hintGrid[counter][1] = row;
                    hintGrid[counter][2] = col;
                    counter++;
                }

            }
        }
        if(counter != 0)
        {
            Random rand = new Random();
            int randomNum = rand.nextInt(counter);
            sudokuModel.setTileValue(hintGrid[randomNum][1], hintGrid[randomNum][2], hintGrid[randomNum][0]);
            sudokuModel.getTile(hintGrid[randomNum][1], hintGrid[randomNum][2]).setCorrectValue(true);
            gridview.updateGridTile(hintGrid[randomNum][1], hintGrid[randomNum][2], hintGrid[randomNum][0]);
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
