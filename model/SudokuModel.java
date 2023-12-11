package model;
import util.SudokuUtilities;

import static util.SudokuUtilities.GRID_SIZE;
import static util.SudokuUtilities.generateSudokuMatrix;
import java.io.Serializable;
import util.SudokuUtilities.SudokuLevel;

/**
 * Represents the model for a Sudoku game, including the game grid and current state.
 */
public class SudokuModel implements Serializable{
    private static int selectedNumber;
    private Tile[][] grid;
    private SudokuLevel level;

    /**
     * Constructs a new SudokuModel with a specified difficulty level.
     *
     * @param level The difficulty level of the Sudoku game.
     */
    public SudokuModel(SudokuUtilities.SudokuLevel level) {
        // Initialize the grid and create Tile objects
        selectedNumber = 0;
        setLevel(level);
        this.grid = fillGridFromMatrix(0);
    }

    /**
     * Fills the Sudoku grid with tiles based on a generated Sudoku matrix.
     *
     * @param mode The mode for generating the Sudoku matrix.
     * @return A 2D array of Tiles representing the Sudoku grid.
     */
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

    /**
     * Retrieves the solution values for the Sudoku grid.
     *
     * @return A 2D array of Tiles representing the solution grid.
     */
    public Tile[][] getSolutionValues(){
        Tile[][] solutionGrid = fillGridFromMatrix(1);;

        return solutionGrid;
    }
    /**
     * Retrieves the current state of the Sudoku grid.
     *
     * @return A 2D array of Tiles representing the current grid.
     */
    public Tile[][] getGrid(){ return grid;}

    /**
     * Prints the current state of the Sudoku grid to the console.
     */
    public void printArray() {
            for (Tile[] row : grid) {
                for (Tile element : row) {
                    System.out.print(element.getValue() + " ");
                }
                System.out.println();
            }
    }

    /**
     * Retrieves a specific tile from the Sudoku grid.
     *
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @return The tile at the specified row and column.
     */
    public Tile getTile(int row, int col) {
        return grid[row][col];
    }
    /**
     * Sets the value of a specific tile in the Sudoku grid.
     *
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @param value The value to set the tile to.
     */
    public void setTileValue(int row, int col, int value)
    {
        grid[row][col].setValue(value);
    }
    /**
     * Clears the value of a specific tile in the Sudoku grid, setting it to zero.
     *
     * @param row The row index of the tile to clear.
     * @param col The column index of the tile to clear.
     */
    public void clearTile(int row, int col)
    {
        if(getTile(row,col).isEditable())
        {
            grid[row][col].setValue(0);
        }
    }

    /**
     * Clears all editable tiles in the Sudoku grid.
     */
    public void clearAllTiles(){
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col].isEditable()){
                    grid[row][col].clearValue();
                }
            }
        }
    }

    /**
     * Gets the currently selected number.
     * 
     * @return The currently selected number.
     */
    public static int getSelectedNumber()
    {
        return selectedNumber;
    }

    /**
     * Sets the Sudoku grid to a new grid.
     * 
     * @param grid The new grid to set.
     */
    public void setGrid(Tile[][] grid) {this.grid = grid;}

    /**
     * Sets the difficulty level of the Sudoku game.
     * 
     * @param level The difficulty level to set.
     */
    public void setLevel(SudokuLevel level) {this.level = level;}

    /**
     * Sets the currently selected number.
     * 
     * @param choice The number to select.
     */
    public static void setSelectedNumber(int choice)
    {
        selectedNumber = choice;
    }

    /**
     * Gets the difficulty level of the Sudoku game.
     * 
     * @return The current difficulty level.
     */
    public SudokuLevel getLevel()
    {
        return level;
    }
    /**
     * Gets all the tiles in the Sudoku grid.
     * 
     * @return A two-dimensional array of Tiles.
     */
    public Tile[][] getTiles()
    {
        return grid;
    }
}
