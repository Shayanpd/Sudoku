package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonToolbar {
    private VBox buttonToolbar;

    public ButtonToolbar() {
        buttonToolbar = new VBox(10);
        buttonToolbar.setAlignment(Pos.CENTER_LEFT);
        buttonToolbar.setPadding(new Insets(20, 0, 0, 0));

        // Create buttons
        Button button1 = new Button("Check");
        Button button2 = new Button("Hint");

        // Add buttons to the VBox
        buttonToolbar.getChildren().addAll(button1, button2);
    }

    public VBox getButtonToolbar() {
        return buttonToolbar;
    }
}
