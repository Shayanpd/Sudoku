package view;

import javafx.event.ActionEvent;
import model.SudokuModel;
import view.Gridview;

public class SudokuController {
    private SudokuModel sudokuModel;
    private Gridview gridview;

    public SudokuController(SudokuModel sudokuModel, Gridview gridview) {
        this.sudokuModel = sudokuModel;
        this.gridview = gridview;
    }

    public void handleTileClick(int row, int col) {
        // Implement this method to handle tile clicks.
        // You can update the model and view here.
        int selectedNumber = SudokuModel.getSelectedNumber();
        if (selectedNumber >= 1 && selectedNumber <= 9) {
            sudokuModel.setTileValue(row, col, selectedNumber);
            gridview.updateTile(row, col, selectedNumber); // Implement this method in Gridview
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

    // Add more controller methods to handle other user interactions as needed.
}
