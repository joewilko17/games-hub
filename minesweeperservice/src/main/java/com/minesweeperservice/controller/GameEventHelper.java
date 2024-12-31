package com.minesweeperservice.controller;

import java.util.Map;

import com.minesweeperservice.model.GameState;
import com.minesweeperservice.model.Player;
import com.minesweeperservice.model.Tile;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameEventHelper {

    private static GameController gameController = GameController.getInstance();
    private static int currentFlagCount = 0;

    public static void handleClicks(MouseEvent event, Tile tile, Boolean firstClick, int maxMines) {

        if (gameController.getGameState().getGameOver()) {
            return;
        }

        if (event.getButton() == MouseButton.PRIMARY) {
            System.out.println(event.isPrimaryButtonDown());
            handleTileLeftClick(tile, firstClick, maxMines);
        }
        if (event.getButton() == MouseButton.SECONDARY) {
            handleTileRightClick(tile, maxMines, gameController.getGameState().getGameStarted());
        }

    }

    // Handles tile action for left click
    public static void handleTileLeftClick(Tile tile, Boolean firstClick, int maxMines) {
        if (firstClick) {
            if (tile.getTileModel().isMine()) {
                gameController.moveFirstClickMine(tile);
            }
            gameController.setFirstClick(false);
            gameController.getGameState().setGameStarted(true);
            gameController.getGameScene().startTimer();
            gameController.calculateAdjacencyValues(gameController.getTiles());
            gameController.revealTiles(tile);
            System.out.println("First Click Status: " + gameController.getFirstClick());
            System.out.println("Game Started Status: " + gameController.getGameState().getGameStarted());
            return;
        }

        if (tile.getTileModel().isMine()) {
            gameController.revealTiles(tile);
            gameController.gameOver();
        }

        if (!tile.getTileModel().isRevealed()) {
            gameController.revealTiles(tile);
        }

        gameController.isLevelComplete(maxMines);
    }

    // Handles tile action for right click
    public static void handleTileRightClick(Tile tile, int maxMines, Boolean gameStarted) {
        if (!gameStarted) {
            gameController.getGameState().setGameStarted(true);
            gameController.getGameScene().startTimer();
            System.out.println("Game Started Status: " + gameController.getGameState().getGameStarted());
        }
        if (!tile.getTileModel().isFlagged() && currentFlagCount < maxMines) {
            tile.toggleFlagged(true);
            currentFlagCount++;

        } else if (tile.getTileModel().isFlagged()) {
            tile.toggleFlagged(false);
            currentFlagCount--;
        }
        System.out.println("Flag Count: " + currentFlagCount + "/" + maxMines);
        gameController.getGameScene().getFlagCountLabel().setText("Flags: " + currentFlagCount + "/" + maxMines);
    }

    public static void resetCurrentFlagCount() {
        currentFlagCount = 0;
    }

    // Static method to save data from the given gamestate to a player object
    public static void savePlayerStats(Player player, GameState gameState) {

        // For the highscores map
        int score = gameState.getPlayerScore();
        // For the player statistics map
        int levelsCompleted = gameState.getLevelsCompleted();
        int minesSwept = gameState.getMinesSwept();
        System.out.println(player.getHighScores());
        player.addHighScore(Integer.toString(player.getHighScores().size() + 1), score);

        Map<String, Integer> playerStats = player.getPlayerStats();
        playerStats.put("Levels Completed", playerStats.getOrDefault("Levels Completed", 0) + levelsCompleted);
        playerStats.put("Mines Swept", playerStats.getOrDefault("Mines Swept", 0) + minesSwept);

        System.out.println(player.getHighScores().toString());
        System.out.println(player.getPlayerStats().toString());
    }

    // Static method to perform score calculation from numerous factors and return a
    // value
    public static int calculateScoreBonus(int score, int timeRemaining, String difficulty) {
        double difficultyMultiplier = 0.0;
        switch (difficulty) {
            case "easy":
                difficultyMultiplier = 1.3;
                break;

            case "medium":
                difficultyMultiplier = 1.3;
                break;

            case "hard":
                difficultyMultiplier = 1.3;
                break;
            default:
                difficultyMultiplier = 0.0;
                break;
        }
        System.out.println("TimeRemaining in seconds" + timeRemaining);
        double timeMultiplier =  1 + (timeRemaining / 100.0);
        double finalScore = (score * difficultyMultiplier * timeMultiplier);
        System.out.println("Difficulty Multiplier: " + difficultyMultiplier);
        System.out.println("Time Multiplier: " + timeMultiplier);
        System.out.println(difficultyMultiplier + " * " + timeMultiplier + " * " + score + " = " + finalScore);
        return (int) finalScore;
    }

}
