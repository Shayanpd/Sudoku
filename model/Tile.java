package model;

public class Tile {
    private int correctValue;
    private int currentValue;
    private boolean isEditable; // Whether the value is editable by the player

    public Tile() {
        // Initialize the tile with no value and as editable
        currentValue = 0;
        isEditable = true;
    }

    public int getValue() {
        return currentValue;
    }

    public void setValue(int value) {
        if (value >= 0 && value <= 9) {
            this.currentValue = value;
        }
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
    public void clearValue() {
        this.currentValue = 0;
    }
    public boolean isFilled() {
        return currentValue != 0;
    }
    
    // Other methods related to a Sudoku tile can be added here
}

