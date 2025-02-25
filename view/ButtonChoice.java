package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.SudokuModel;
import view.SudokuController;

/**
 * This class represents a set of buttons for selecting numbers in the Sudoku game.
 */
public class ButtonChoice {
    private VBox buttonChoice;


    /**
     * Constructor for ButtonChoice.
     * Initializes the buttons for number selection and the clear button.
     */
    public ButtonChoice() {
        buttonChoice = new VBox(2);
        buttonChoice.setAlignment(Pos.CENTER);
        buttonChoice.setPadding(new Insets(0, 10, 0, 10));

        // Create buttons
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button buttonC = new Button("C");

        button1.setOnAction(event -> {
            System.out.println("1 button clicked!");
            SudokuController.selectedNumber = 1;
        });
        button2.setOnAction(event -> {
            System.out.println("2 button clicked!");
            SudokuController.selectedNumber = 2;
        });
        button3.setOnAction(event -> {
            System.out.println("3 button clicked!");
            SudokuController.selectedNumber = 3;
        });
        button4.setOnAction(event -> {
            System.out.println("4 button clicked!");
            SudokuController.selectedNumber = 4;
        }); 
        button5.setOnAction(event -> {
            System.out.println("5 button clicked!");
            SudokuController.selectedNumber = 5;
        });
        button6.setOnAction(event -> {
            System.out.println("6 button clicked!");
            SudokuController.selectedNumber = 6;
        });
        button7.setOnAction(event -> {
            System.out.println("7 button clicked!");
            SudokuController.selectedNumber = 7;
        });
        button8.setOnAction(event -> {
            System.out.println("8 button clicked!");
            SudokuController.selectedNumber = 8;
        });
        button9.setOnAction(event -> {
            System.out.println("9 button clicked!");
            SudokuController.selectedNumber = 9;
        });
        buttonC.setOnAction(event -> {
            System.out.println("C button clicked!");
            SudokuController.selectedNumber = 0;
        });

        // Add buttons to the VBox
        buttonChoice.getChildren().addAll(button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonC);
    }

    /**
     * Gets the VBox containing all the number selection buttons.
     * 
     * @return A VBox with all number buttons.
     */
    public VBox getButtonChoice() {
        return buttonChoice;
    }
}
