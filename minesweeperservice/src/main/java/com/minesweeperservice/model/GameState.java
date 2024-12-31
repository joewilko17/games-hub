package com.minesweeperservice.model;

public class GameState {

    private Boolean gameStarted;
    private Boolean gameOver;
    private int levelsCompleted;
    private int playerScore;
    private int minesSwept;
    private int timeRemaining;
    
    public GameState(Boolean gameStarted, Boolean gameOver, int levelsCompleted, int playerScore, int minesSwept,
            int timeRemaining) {
        this.gameStarted = gameStarted;
        this.gameOver = gameOver;
        this.levelsCompleted = levelsCompleted;
        this.playerScore = playerScore;
        this.minesSwept = minesSwept;
        this.timeRemaining = timeRemaining;
    }

    public Boolean getGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(Boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getLevelsCompleted() {
        return levelsCompleted;
    }

    public void setLevelsCompleted(int levelsCompleted) {
        this.levelsCompleted = levelsCompleted;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getMinesSwept() {
        return minesSwept;
    }

    public void setMinesSwept(int minesSwept) {
        this.minesSwept =  this.minesSwept + minesSwept;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    @Override
    public String toString() {
        return "GameState [gameStarted=" + gameStarted + ", gameOver=" + gameOver + ", levelsCompleted="
                + levelsCompleted + ", playerScore=" + playerScore + ", minesSwept=" + minesSwept + ", timeRemaining="
                + timeRemaining + "]";
    } 

    // public void resetGameState(Boolean gameStarted, Boolean gameOver, int playerScore, int timeRemaining) {
    //     this.gameStarted = gameStarted;
    //     this.gameOver = gameOver;
    //     this.playerScore = playerScore;
    //     this.timeRemaining = timeRemaining;
    // }
}
