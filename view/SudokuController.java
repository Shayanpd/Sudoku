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

public class SudokuController {
    private SudokuModel sudokuModel;
    private final Gridview gridview;
    private final FileChooser fileChooser;
    public static int selectedNumber;

    public SudokuController(SudokuModel sudokuModel, Gridview gridview) {
        this.sudokuModel = sudokuModel;
        this.gridview = gridview;
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Open A Sudoku File");
        this.fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Sudoku Files", "*.sudoku" ));
    }

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
    public void handleSelectedNumberChange(int selectedNumber) {
        // Implement this method to handle changes in the selected number.
        SudokuModel.setSelectedNumber(selectedNumber);
    }

    public SudokuModel createNewModel(SudokuUtilities.SudokuLevel level){
        return new SudokuModel(level);
    }

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
            gridview.updateGridTile(hintGrid[randomNum][1], hintGrid[randomNum][2], hintGrid[randomNum][0]);
        }
    }
    public SudokuModel getModel()
    {
        return sudokuModel;
    }
    public void setModel(SudokuModel sudokuModel)
    {
        this.sudokuModel = sudokuModel; 
        //updatefrommodel
    }
}
