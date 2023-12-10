package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.SudokuModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import util.SudokuUtilities;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        SudokuModel sudokuModel = new SudokuModel(SudokuUtilities.SudokuLevel.EASY);

        // Create the Gridview
        Gridview gridView = new Gridview(sudokuModel);
        TilePane numberPane = gridView.getNumberPane();

        BorderPane root = new BorderPane();

        // Create the MenuBar component
        MenuBarComponent menuBarComponent = new MenuBarComponent(gridView, sudokuModel);
        root.setTop(menuBarComponent.getMenuBar());

        // Create the ButtonToolbar component
        ButtonToolbar buttonToolbar = new ButtonToolbar();
        // Create the ButtonToolbar component
        ButtonChoice buttonChoice = new ButtonChoice();

        // Set ButtonToolbar on the left and Gridview on the center
        root.setLeft(buttonToolbar.getButtonToolbar());
        root.setCenter(numberPane);
        root.setRight(buttonChoice.getButtonChoice());
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
