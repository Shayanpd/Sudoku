package model;

import java.io.Serializable;

/**
 * Represents a single tile in a Sudoku puzzle.
 * A tile holds a value, and information about whether it is editable and whether its current value is correct.
 */
public class Tile implements Serializable {
    private int currentValue;
    private boolean isCorrectValue = true;
    private boolean isEditable; // Whether the value is editable by the player

    /**
     * Constructs a Tile with default settings.
     * Initially, the tile has no value (0) and is set as editable.
     */
    public Tile() {
        this.currentValue = 0;
        this.isEditable = true;
    }

    /**
     * Checks if the current value of the tile is correct.
     * 
     * @return true if the current value is correct, false otherwise.
     */
    public boolean isCorrectValue() {
        return isCorrectValue;
    }

    /**
     * Sets whether the current value of the tile is correct.
     * 
     * @param correctValue true if the current value is correct, false otherwise.
     */
    public void setCorrectValue(boolean correctValue) {
        isCorrectValue = correctValue;
    }

    /**
     * Retrieves the current value of the tile.
     * 
     * @return The current value of the tile.
     */
    public int getValue() {
        return currentValue;
    }

    /**
     * Sets the value of the tile. The value must be between 0 and 9.
     * 
     * @param value The value to set for the tile.
     */
    public void setValue(int value) {
        if (value >= 0 && value <= 9) {
            this.currentValue = value;
        }
    }

    /**
     * Checks if the tile is editable.
     * 
     * @return true if the tile is editable, false otherwise.
     */
    public boolean isEditable() {
        return isEditable;
    }

    /**
     * Sets whether the tile is editable.
     * 
     * @param isEditable true if the tile should be editable, false otherwise.
     */
    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * Clears the current value of the tile, setting it to 0.
     */
    public void clearValue() {
        this.currentValue = 0;
    }

    /**
     * Checks if the tile is filled with a non-zero value.
     * 
     * @return true if the tile is filled, false if the value is 0.
     */
    public boolean isFilled() {
        return currentValue != 0;
    }
}

