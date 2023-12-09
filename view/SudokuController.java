package view;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.SudokuModel;
import util.SudokuUtilities;
import view.Gridview;

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
        if (selectedNumber >= 1 && selectedNumber <= 9) {
            sudokuModel.setTileValue(row, col, selectedNumber);
            gridview.updateGridTile(row, col, selectedNumber);
                //update sudokumodel
        } else {
            sudokuModel.clearTile(row, col);
            gridview.clearTile(row, col); // Implement this method in Gridview
        }
        System.out.println("Set" + selectedNumber + " at Row: " + row + " Column: " + col);
    }
    
    public void handleSelectedNumberChange(int selectedNumber) {
        // Implement this method to handle changes in the selected number.
        SudokuModel.setSelectedNumber(selectedNumber);
    }

    public SudokuModel createNewModel(SudokuUtilities.SudokuLevel level){
        return new SudokuModel(level);
    }

    private void checkIfWon()
    {
        if(sudokuModel.isSolved())
        {
            //view.showGameWinAlert();
        }
    }

    public void handleHint()
    {
        //kod för att hantera hint
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
    // Add more controller methods to handle other user interactions as needed.
}
