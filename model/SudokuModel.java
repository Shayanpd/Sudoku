package model;
import util.SudokuUtilities;

import static util.SudokuUtilities.generateSudokuMatrix;

public class SudokuModel {
    private static int selectedNumber;
    private Tile[][] grid;

    public SudokuModel(SudokuUtilities.SudokuLevel level) {
        // Initialize the grid and create Tile objects
        selectedNumber = 0;
        grid = new Tile[9][9];

        int[][][] sudokuMatrix = generateSudokuMatrix(level); // Get the initial layout based on the difficulty level

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int initialValue = sudokuMatrix[row][col][0]; // Get the initial value from the matrix
                boolean isEditable = initialValue == 0; // Determine if the tile is editable based on its initial value

                grid[row][col] = new Tile();
                grid[row][col].setValue(initialValue);
                grid[row][col].setEditable(isEditable);
            }
        }
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }
    public void setTileValue(int row, int col, int value)
    {
        grid[row][col].setValue(value);
    }
    public void clearTile(int row, int col)
    {
        grid[row][col].setValue(0);
    }
    public static int getSelectedNumber()
    {
        return selectedNumber;
    }
    public static void setSelectedNumber(int choice)
    {
        selectedNumber = choice;
    }
}
