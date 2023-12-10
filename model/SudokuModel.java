package model;
import util.SudokuUtilities;

import static util.SudokuUtilities.GRID_SIZE;
import static util.SudokuUtilities.generateSudokuMatrix;
import java.io.Serializable;
import util.SudokuUtilities.SudokuLevel;

public class SudokuModel implements Serializable{
    private static int selectedNumber;
    private Tile[][] grid;
    private SudokuLevel level;

    public SudokuModel(SudokuUtilities.SudokuLevel level) {
        // Initialize the grid and create Tile objects
        selectedNumber = 0;
        setLevel(level);
        this.grid = fillGridFromMatrix(0);
    }

    private Tile[][] fillGridFromMatrix(int mode){
        Tile[][] temp = new Tile[GRID_SIZE][GRID_SIZE];

        int[][][] sudokuMatrix = generateSudokuMatrix(getLevel());

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int initialValue = sudokuMatrix[row][col][mode]; // Get the initial value from the matrix
                boolean isEditable = initialValue == 0; // Determine if the tile is editable based on its initial value

                temp[row][col] = new Tile();
                temp[row][col].setValue(initialValue);
                temp[row][col].setEditable(isEditable);
            }
        }

        return temp;
    }

    public Tile[][] getSolutionValues(){
        Tile[][] solutionGrid = fillGridFromMatrix(1);;

        return solutionGrid;
    }
    public Tile[][] getGrid(){ return grid;}
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
    public void clearAllTiles(){
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col].isEditable()){
                    grid[row][col].clearValue();
                }
            }
        }
    }
    public static int getSelectedNumber()
    {
        return selectedNumber;
    }
    public void setGrid(Tile[][] grid) {this.grid = grid;}
    public void setLevel(SudokuLevel level) {this.level = level;}
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
