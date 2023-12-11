package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * This class represents the toolbar with buttons for various actions in the Sudoku game.
 */
public class ButtonToolbar {
      private VBox buttonToolbar;

    /**
     * Constructor for ButtonToolbar.
     * Initializes the toolbar with action buttons for the Sudoku game.
     * 
     * @param gridview The Gridview associated with this toolbar for handling button actions.
     */
    public ButtonToolbar(Gridview gridview) {
        buttonToolbar = new VBox(10);
        buttonToolbar.setAlignment(Pos.CENTER);
        buttonToolbar.setPadding(new Insets(0, 10, 0, 10));

        // Create buttons
        Button button1 = new Button("Check");
        Button button2 = new Button("Hint");

        button1.setOnAction(event -> {
            gridview.getController().compareCurrentGridToSolutionValues();
            gridview.updateViewModel();
            System.out.println("Check button clicked!");
        });
        button2.setOnAction(event -> {
            gridview.getController().handleHint();
            System.out.println("Hint button clicked!");
        });

        // Add buttons to the VBox
        buttonToolbar.getChildren().addAll(button1, button2);
    }

    /**
     * Gets the VBox containing all the buttons in the toolbar.
     * 
     * @return A VBox with all action buttons.
     */
    public VBox getButtonToolbar() {
        return buttonToolbar;
    }
}
