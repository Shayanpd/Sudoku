package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonToolbar {
      private VBox buttonToolbar;

    public ButtonToolbar(Gridview gridview) {
        buttonToolbar = new VBox(10);
        buttonToolbar.setAlignment(Pos.CENTER);
        buttonToolbar.setPadding(new Insets(0, 10, 0, 10));

        // Create buttons
        Button button1 = new Button("Check");
        Button button2 = new Button("Hint");

        button1.setOnAction(event -> {
            System.out.println("Check button clicked!");
        });
        button2.setOnAction(event -> {
            System.out.println("Hint button clicked!");
            gridview.getController().handleHint();
        });

        // Add buttons to the VBox
        buttonToolbar.getChildren().addAll(button1, button2);
    }

    public VBox getButtonToolbar() {
        return buttonToolbar;
    }
}
