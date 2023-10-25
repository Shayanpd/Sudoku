package model;

public class SudokuModel {
    private static int selectedNumber;
    private Tile[][] grid;

    public SudokuModel() {
        // Initialize the grid and create Tile objects
        grid = new Tile[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                grid[row][col] = new Tile();
            }
        }
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
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
