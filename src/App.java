import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import view.ButtonToolbar;
import view.Gridview;
import view.MenuBarComponent;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Create the MenuBar component
        MenuBarComponent menuBarComponent = new MenuBarComponent();
        root.setTop(menuBarComponent.getMenuBar());

        // Create the Gridview
        Gridview gridView = new Gridview();
        TilePane numberPane = gridView.getNumberPane();

        // Create the ButtonToolbar component
        ButtonToolbar buttonToolbar = new ButtonToolbar();

        // Set ButtonToolbar on the left and Gridview on the center
        root.setLeft(buttonToolbar.getButtonToolbar());
        root.setCenter(numberPane);

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
