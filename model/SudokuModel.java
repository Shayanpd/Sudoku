package model;
import util.SudokuUtilities;
import util.SudokuUtilities.SudokuLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static util.SudokuUtilities.GRID_SIZE;

/**
 * Represents the model for a Sudoku game, including the game grid and current state.
 */
public class SudokuModel implements Serializable {
    private static int selectedNumber;
    private Tile[][] grid;
    private Tile[][] solutionGrid; // Separate grid to store the solution
    private SudokuLevel level;

    /**
     * Constructs a new SudokuModel with a specified difficulty level.
     *
     * @param level The difficulty level of the Sudoku game.
     */
    public SudokuModel(SudokuUtilities.SudokuLevel level) {
        selectedNumber = 0;
        setLevel(level);
        initializeGrids();
    }

    /**
     * Initializes the Sudoku grid and solution grid based on a generated Sudoku matrix.
     */
    private void initializeGrids() {
        int[][][] sudokuMatrix = SudokuUtilities.generateSudokuMatrix(level);

        grid = new Tile[GRID_SIZE][GRID_SIZE];
        solutionGrid = new Tile[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int initialValue = sudokuMatrix[row][col][0];
                int solutionValue = sudokuMatrix[row][col][1];
                boolean isEditable = initialValue == 0;

                grid[row][col] = new Tile(initialValue, isEditable);
                solutionGrid[row][col] = new Tile(solutionValue, false); // Non-editable solution tiles
            }
        }
    }

    /**
     * Retrieves the solution values for the Sudoku grid.
     *
     * @return A 2D array of Tiles representing the solution grid.
     */
    public Tile[][] getSolutionValues() {
        return solutionGrid;
    }

    /**
     * Provides a hint by finding an empty or incorrect tile and returning its correct value.
     * 
     * @return An array containing the row index, column index, and correct value of the tile,
     *         or null if no hint is available.
     */
    public int[] provideHint() {
        List<int[]> possibleHints = new ArrayList<>();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Tile currentTile = grid[row][col];
                if (currentTile.isEditable() && currentTile.getValue() != solutionGrid[row][col].getValue()) {
                    possibleHints.add(new int[] { row, col, solutionGrid[row][col].getValue() });
                }
            }
        }

        if (possibleHints.isEmpty()) {
            return null; // No hint available
        }

        Random random = new Random();
        return possibleHints.get(random.nextInt(possibleHints.size()));
    }

    /**
     * Fills the Sudoku grid with tiles based on a generated Sudoku matrix.
     *
     * @param mode The mode for generating the Sudoku matrix.
     * @return A 2D array of Tiles representing the Sudoku grid.
     */

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
