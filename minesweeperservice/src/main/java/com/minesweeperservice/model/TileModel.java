package com.minesweeperservice.model;

public class TileModel {

    private boolean isRevealed;
    private boolean isMine;
    private boolean isFlagged;
    private int adjacentBombs;

    public TileModel(boolean isMine) {
        this.isMine = isMine;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    public void setAdjacentBombs(int adjacentBombs) {
        this.adjacentBombs = adjacentBombs;
    }

}
