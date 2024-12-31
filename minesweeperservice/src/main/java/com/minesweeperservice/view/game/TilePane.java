package com.minesweeperservice.view.game;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TilePane extends StackPane {

    private Label tileLabel;

    public TilePane(String labelText) {

        buildTile(labelText);
    }

    public void setLabel(String text) {
        tileLabel.setText(text);
    }
    
    private void buildTile(String labelText) {
        this.setPrefSize(30, 30);
        tileLabel = new Label(labelText);
        this.getChildren().add(tileLabel);
        this.getStylesheets().add(getClass().getResource("/com/gameshub/styles/dark.css").toExternalForm());
        this.getStyleClass().add("tile-unrevealed");
    }
}
