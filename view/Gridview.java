package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.SudokuModel;
import model.Tile;
import util.SudokuUtilities;
import util.SudokuUtilities.SudokuLevel;


public class Gridview {
    private static final int GRID_SIZE = SudokuUtilities.GRID_SIZE;
    private static final int SECTIONS_PER_ROW = SudokuUtilities.SECTIONS_PER_ROW;
    private static final int SECTION_SIZE = SudokuUtilities.SECTION_SIZE;
    private Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private SudokuController controller;
    private SudokuModel sudokuModel;

    
    public Gridview(SudokuModel sudokuModel) {
        this.sudokuModel = sudokuModel;
        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        controller = new SudokuController(sudokuModel, this);
        initNumberTiles();
        // ...
        numberPane = makeNumberPane();
        // ...
    }

    public void updateGridTile(int row, int col, int value) {
        if(value >= 1 && value <= 9 ){

            numberTiles[row][col].setText(String.valueOf(value));
            if(sudokuModel.getTile(row, col).isEditable() && sudokuModel.getTile(row, col).isCorrectValue()){

                String existingStyle = numberTiles[row][col].getStyle();
                numberTiles[row][col].setStyle(existingStyle + "-fx-text-fill: blue;");
            }
            else if (sudokuModel.getTile(row, col).isEditable() && !sudokuModel.getTile(row, col).isCorrectValue()) {
                String existingStyle = numberTiles[row][col].getStyle();
                numberTiles[row][col].setStyle(existingStyle + "-fx-text-fill: red;");
            }
        }
        else{
            clearTile(row, col);
        }
    }

    public void clearTile(int row, int col) {
        sudokuModel.getTile(row, col).clearValue();
        numberTiles[row][col].setText("");
        String existingStyle = numberTiles[row][col].getStyle();
        numberTiles[row][col].setStyle(existingStyle + "-fx-text-fill: black;");
    }
    private EventHandler<MouseEvent> tileClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Label clickedTile = (Label) event.getSource();
            
            // Iterate through the numberTiles array to find the clicked tile's position
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    if (clickedTile == numberTiles[row][col]) {
                        // You have the row and column of the clicked tile
                        // Now you can perform any action you want
                        // For example, print the row and column to the console
                        System.out.println("Clicked on tile at Row: " + row + " Column: " + col);

                        // Handle the click in the controller
                        controller.handleTileClick(row, col);
                        return; // Exit the loop once found
                    }
                }
            }
        }
    };
    // use this method to get a reference to the number (called by some other class)
    public TilePane getNumberPane() {
        return numberPane;
    }
    
    // called by constructor (only)
    private final void initNumberTiles() {
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);
    
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Label tile = new Label(/* add number, or "", to display */); // data from model
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }
    
    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SECTIONS_PER_ROW);
        root.setPrefRows(SECTIONS_PER_ROW);
        root.setStyle(
                "-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");
    
        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SECTIONS_PER_ROW][SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SECTION_SIZE);
                section.setPrefRows(SECTION_SIZE);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 1.0px;");
    
                // add number tiles to this section
                for (int row = 0; row < SECTION_SIZE; row++) {
                    for (int col = 0; col < SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SECTION_SIZE + row][scol * SECTION_SIZE + col]);
                    }
                }
    
                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }
    
        return root;
    }

    public SudokuModel getModel()
    {
        return this.sudokuModel;
    }
    public void newModel(SudokuModel sudokuModel)
    {
        this.sudokuModel = sudokuModel;
        colorReset();
        updateViewModel();
    }
    public void colorReset()
    {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                numberTiles[row][col].setText("");
                String existingStyle = numberTiles[row][col].getStyle();
                numberTiles[row][col].setStyle(existingStyle + "-fx-text-fill: black;");
                }
            }
    }

    public void updateViewModel()
    {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++)
        {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++)
            {
                    updateGridTile(row, col, sudokuModel.getTile(row, col).getValue());

            }
        }
    }

    public SudokuController getController()
    {
        return controller;
    }
}
