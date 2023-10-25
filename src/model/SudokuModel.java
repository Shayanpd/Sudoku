package model;

public class SudokuModel {
    private static int selectedNumber;

    public static int getSelectedNumber()
    {
        return selectedNumber;
    }
public static void setSelectedNumber(int choice)
    {
        selectedNumber = choice;
    }
}
