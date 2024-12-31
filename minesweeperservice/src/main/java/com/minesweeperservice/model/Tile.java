package com.minesweeperservice.model;

import com.minesweeperservice.view.game.TilePane;

public class Tile {
    private final TileModel model;
    private final TilePane tilePane;
    private int row;
    private int col;

    public Tile(TileModel model, TilePane tilePane) {
        this.model = model;
        this.tilePane = tilePane;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public TileModel getTileModel() {
        return model;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public void updateView() {
        if (getTileModel().isRevealed()) {
            if (model.isMine()) {
                getTilePane().setLabel("\uD83D\uDCA5");
                System.out.println("Mine Tile Revealed");
                getTilePane().getStyleClass().clear();
                getTilePane().getStyleClass().add("tile-revealed-mine");
            } else {
                if(getTileModel().getAdjacentBombs() != 0) {
                    getTilePane().setLabel(Integer.toString(getTileModel().getAdjacentBombs()));
                }
                System.out.println("Empty Tile Revealed");
                getTilePane().getStyleClass().clear();
                getTilePane().getStyleClass().add("tile-revealed-empty");
            }
        }
    }

    // Toggles flagged status for unrevealed tiles
    public void toggleFlagged(boolean flagged) {
        if (flagged && !getTileModel().isRevealed()) {
            getTileModel().setFlagged(true);
            getTilePane().setLabel("\uD83D\uDEA9");
            System.out.println("Tile has been flagged");
        }
        if (!flagged && !getTileModel().isRevealed()) {
            getTileModel().setFlagged(false);
            getTilePane().setLabel("");
            System.out.println("Tile has been unflagged");
        }
    }

}
