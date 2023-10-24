import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import view.ButtonToolbar;
import view.MenuBarComponent;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Create the MenuBar component
        MenuBarComponent menuBarComponent = new MenuBarComponent();
        root.setTop(menuBarComponent.getMenuBar());

        // Create the ButtonToolbar component
        ButtonToolbar buttonToolbar = new ButtonToolbar();
        root.setCenter(buttonToolbar.getButtonToolbar());

        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
