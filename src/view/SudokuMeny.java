package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class SudokuMeny extends Application {
    @Override
    public void start(Stage stage) {
        // Skapa en layout-container (VBox)
        VBox root = new VBox();

        // Skapa knappar
        Button button1 = new Button("Check");
        Button button2 = new Button("Hint");

        button1.setOnAction(event -> {
            // Här kan du ange koden som ska köras när knappen klickas
            System.out.println("Knapp 1 klickades!");
        });
        button2.setOnAction(event -> {
            // Här kan du ange koden som ska köras när knappen klickas
            System.out.println("Knapp 2 klickades!");
        });

        // Lägg till knapparna i layouten
        root.getChildren().addAll(button1, button2);

        // Skapa en scen och sätt layouten som dess rot
        Scene scene = new Scene(root, 300, 200);

        // Sätt scenen för scenen
        stage.setScene(scene);

        // Ange titeln för fönstret
        stage.setTitle("JavaFX Button App");

        // Visa fönstret
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
