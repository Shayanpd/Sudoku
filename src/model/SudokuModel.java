package model;

public class SudokuModel {
    private static int selectedNumber;
    private Tile[][] grid;

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
