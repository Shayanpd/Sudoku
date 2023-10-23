package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class SudokuMeny extends Application {
    @Override
    public void start(Stage stage) {
        // Create a layout-container (BorderPane)
        BorderPane root = new BorderPane();

        // Create a VBox for the buttons
        VBox buttonBox = new VBox(10); // 10 is the spacing between buttons
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(20));

        // Create buttons
        Button button1 = new Button("Check");
        Button button2 = new Button("Hint");

        button1.setOnAction(event -> {
            System.out.println("Check button clicked!");
        });

        button2.setOnAction(event -> {
            System.out.println("Hint button clicked!");
        });

        // Create an HBox for the "Hint" button with left padding
        HBox hintBox = new HBox(10);
        hintBox.setPadding(new Insets(0, 0, 0, 5));
        hintBox.getChildren().add(button2);

        // Add buttons to the VBox
        buttonBox.getChildren().add(button1);

        // Add the HBox containing the "Hint" button to the VBox
        buttonBox.getChildren().add(hintBox);

        // Place the VBox in the center of the BorderPane
        root.setCenter(buttonBox);

        // Create a scene and set the layout as its root
        Scene scene = new Scene(root, 300, 200);

        // Set the scene for the stage
        stage.setScene(scene);

        // Set the window title
        stage.setTitle("JavaFX Button App");

        // Show the window
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
