package model;

public class Tile {
    private int value;
    private boolean isEditable; // Whether the value is editable by the player

    public Tile() {
        // Initialize the tile with no value and as editable
        value = 0;
        isEditable = true;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        }
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }
    public void clearValue() {
        this.value = 0;
    }
    public boolean isFilled() {
        return value != 0;
    }
    
    // Other methods related to a Sudoku tile can be added here
}

