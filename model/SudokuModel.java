package model;

import java.io.Serializable;

import util.SudokuUtilities.SudokuLevel;

public class SudokuModel implements Serializable{
    private static int selectedNumber;
    private Tile[][] grid;
    private SudokuLevel level;

    public SudokuModel() {
        // Initialize the grid and create Tile objects
        selectedNumber = 0;
        grid = new Tile[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new Tile();
            }
        }
    }
    public void printArray() {
            for (Tile[] row : grid) {
                for (Tile element : row) {
                    System.out.print(element.getValue() + " ");
                }
                System.out.println();
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
        if(getTile(row,col).isEditable())
        {
            grid[row][col].setValue(0);
        }
    }
    public static int getSelectedNumber()
    {
        return selectedNumber;
    }
    public void setSudokuModel(Tile[][] grid, SudokuLevel level)
    {
        this.grid = grid;
        this.level = level;
    }
    public static void setSelectedNumber(int choice)
    {
        selectedNumber = choice;
    }
    public SudokuLevel getLevel()
    {
        return level;
    }
        public Tile[][] getTiles()
    {
        return grid;
    }
    public boolean isSolved()
    {
        //check if the sudokumodel is solved.
        return true;
    }
}
