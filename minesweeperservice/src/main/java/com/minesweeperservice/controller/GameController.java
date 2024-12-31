package com.minesweeperservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.minesweeperservice.model.GameState;
import com.minesweeperservice.model.Tile;
import com.minesweeperservice.model.TileModel;
import com.minesweeperservice.view.GameScene;
import com.minesweeperservice.view.game.TilePane;

import javafx.scene.layout.GridPane;

public class GameController {

    private static GameController instance;
    private GameState gameState;
    private GameScene gameScene;
    private StageManager stageManager;
    private SceneManager sceneManager;
    private ProfileManager profileManager;
    private Tile[][] tiles;
    private GridPane gameGrid;
    private String selectedDifficulty;
    private Boolean firstClick;
    private int rows;
    private int cols;

    private GameController() {
        gameGrid = new GridPane();
        firstClick = true;
        stageManager = StageManager.getInstance();
        sceneManager = SceneManager.getInstance(stageManager.getMainStage());
        profileManager = ProfileManager.getInstance();
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public Boolean getFirstClick() {
        return firstClick;
    }

    public void setFirstClick(Boolean firstClick) {
        this.firstClick = firstClick;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public GridPane getGameGrid() {
        return gameGrid;
    }

    public String getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public void setSelectedDifficulty(String difficulty) {
        this.selectedDifficulty = difficulty;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Takes in a tile which it sets to Empty , then finds the first tile not
    // containing a mine , and sets it to a mine
    public void moveFirstClickMine(Tile tile) {
        System.out.println("Mined Tile was clicked first, now moving...");
        tile.getTileModel().setMine(false);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tileToReplace = tiles[row][col];
                if (!tileToReplace.getTileModel().isMine()) {
                    tileToReplace.getTileModel().setMine(true);
                    tileToReplace.getTileModel().setAdjacentBombs(-1);
                    tileToReplace.getTileModel().setRevealed(false);
                    System.out.println("Mined Tile has been moved to position: " + row + "/" + col);
                    calculateAdjacencyValues(tiles);
                    return;
                }
            }
        }
    }

    public void createGrid(int rows, int cols, int maxMines) {
        gameGrid.getChildren().clear(); // Resets game grid from previous levels
        this.rows = rows;
        this.cols = cols;
        tiles = new Tile[this.rows][this.cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TileModel model = new TileModel(false);
                TilePane tilePane = new TilePane("");
                Tile tile = new Tile(model, tilePane);
                tile.setRow(row);
                tile.setCol(col);
                tiles[row][col] = tile;

                tile.getTilePane()
                        .setOnMouseClicked(event -> GameEventHelper.handleClicks(event, tile, firstClick, maxMines));

                gameGrid.add(tile.getTilePane(), col, row);
            }
        }
        populateMines(rows, cols, maxMines);
        debugPopulateSpecificMine(false); // Specific set board for development 
        gameState = new GameState(false, false, 0, 0, 0, 120);
        setGameState(gameState);
        System.out.println(getGameState().toString());
        GameEventHelper.resetCurrentFlagCount();
    }

    private void populateMines(int rows, int cols, int maxMines) {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < maxMines) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            Tile tile = tiles[row][col];
            if (!tile.getTileModel().isMine()) {
                tile.getTileModel().setMine(true);
                placedMines++;
                System.out.println(placedMines);
            }
        }
    }

    // Check if the current level has been completed successfully, called on each
    // handleLeftClick()
    public void isLevelComplete(int maxMines) {

        if (!getGameState().getGameOver()) {
            int targetRevealedTiles = ((rows * cols) - maxMines);
            // Check how many mines have been revealed + how many tiles have been revealed
            int revealedTileCount = getRevealedTilesCount(getTiles());
            int unrevealedMineCount = getUnrevealedMineCount(getTiles());
            // If the level is complete then reset the grid with a new grid + time remaining
            // but keep the score accumulating
            if (unrevealedMineCount == maxMines && revealedTileCount == targetRevealedTiles) {
                // resetGame but with not wiping score
                // getGameScene().stopTimer();
                System.out.println("Level " + getGameState().getLevelsCompleted() + " Completed");

                int score = getGameScene().getScoreComponent().getScore();
                
                getGameState().setPlayerScore(score);

                int levelsCompleted = getGameState().getLevelsCompleted();
                getGameState().setLevelsCompleted(++levelsCompleted);

                int minesSwept = getGameState().getMinesSwept() + maxMines;
                System.out.println("Levels Completed: " + levelsCompleted);
                System.out.println("Score: " + getGameState().getPlayerScore());
                System.out.println("Mines Swept: " + minesSwept);

                String timeRemaining = getGameScene().getTimerLabel().getText();
                int timeRemainingInt = getGameScene().getTimerComponent().timeRemainingProperty().get();
                getGameState().setTimeRemaining(getGameScene().getTimerComponent().timeRemainingProperty().get());
                System.out.println(timeRemaining);

                getGameScene().resetGame(selectedDifficulty, true);
                // Implement solution to set time to specific values for different difficulties
                getGameScene().getTimerComponent().addTime(60);
                score = GameEventHelper.calculateScoreBonus(score, timeRemainingInt, getSelectedDifficulty());
                System.out.println(score);
                getGameState().setPlayerScore(score);
                getGameState().setLevelsCompleted(levelsCompleted);
                getGameState().setMinesSwept(minesSwept);
                // set gamescene labels to correct values
                getGameScene().getLevelsCompletedLabel().setText("Level: " + levelsCompleted);
                getGameScene().getScoreComponent().setScore(score);
                System.out.println("GameState after level completed: " + getGameState().toString());
                return;
            }
            // If the level is not, do nothing
            System.out
                    .println("Game is not completed: " + unrevealedMineCount + "/" + maxMines + " Mines remaining, "
                            + revealedTileCount + "/" + targetRevealedTiles
                            + " tiles revealed");
        }

    }

    public int getRevealedTilesCount(Tile[][] tiles) {
        int revealedTileCount = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = tiles[row][col];
                if (tile.getTileModel().isRevealed()) {
                    revealedTileCount++;
                }
            }
        }
        return revealedTileCount;
    }

    public int getUnrevealedMineCount(Tile[][] tiles) {
        int unrevealedMineCount = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = tiles[row][col];
                if (tile.getTileModel().isMine() && !tile.getTileModel().isRevealed()) {
                    unrevealedMineCount++;
                }
            }
        }
        return unrevealedMineCount;
    }

    public void calculateAdjacencyValues(Tile[][] tiles) {
        int rows = tiles.length;
        int cols = tiles[0].length;
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = tiles[row][col];
                if (tile.getTileModel().isMine()) {
                    tile.getTileModel().setAdjacentBombs(-1);
                    continue;
                }
                int adjacentMines = 0;
                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        if (tiles[newRow][newCol].getTileModel().isMine()) {
                            adjacentMines++;
                        }
                    }
                }
                if (adjacentMines > 0) {
                    tile.getTileModel().setAdjacentBombs(adjacentMines);
                    tile.updateView();
                }

            }
        }
    }

    public void revealTiles(Tile clickedTile) {
        if (clickedTile.getTileModel().isRevealed())
            return;

        clickedTile.getTileModel().setRevealed(true);
        clickedTile.updateView();
        if (!clickedTile.getTileModel().isMine()) {
            gameScene.getScoreComponent().updateScore(10);
        }

        System.out.println("Revealing tile at (" + clickedTile.getRow() + ", " + clickedTile.getCol() + ")");

        if (clickedTile.getTileModel().getAdjacentBombs() == 0) {
            for (Tile neighbour : getNeighbours(clickedTile, tiles)) {
                if (!neighbour.getTileModel().isRevealed()) {
                    revealTiles(neighbour);
                }
            }

        }
    }

    private List<Tile> getNeighbours(Tile tile, Tile[][] tiles) {
        List<Tile> neighbors = new ArrayList<>();
        int rows = tiles.length;
        int cols = tiles[0].length;
        int x = tile.getRow();
        int y = tile.getCol();

        int[] dx = { 1, -1, 0, 0, 1, -1, 1, -1 };
        int[] dy = { 0, 0, 1, -1, 1, -1, -1, 1 };

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                neighbors.add(tiles[nx][ny]);
            }
        }

        return neighbors;
    }

    private void debugPopulateSpecificMine(boolean active) {
        if (active) {
            Tile tile10 = tiles[5][6];
            tile10.getTileModel().setMine(true);
            Tile tile1 = tiles[8][8];
            tile1.getTileModel().setMine(true);
            Tile tile2 = tiles[7][7];
            tile2.getTileModel().setMine(true);
            Tile tile3 = tiles[6][6];
            tile3.getTileModel().setMine(true);
            Tile tile4 = tiles[5][5];
            tile4.getTileModel().setMine(true);
            Tile tile5 = tiles[4][4];
            tile5.getTileModel().setMine(true);
            Tile tile6 = tiles[3][3];
            tile6.getTileModel().setMine(true);
            Tile tile7 = tiles[2][2];
            tile7.getTileModel().setMine(true);
            Tile tile8 = tiles[1][1];
            tile8.getTileModel().setMine(true);
            Tile tile9 = tiles[0][0];
            tile9.getTileModel().setMine(true);
        }
    }

    public void gameOver() {
        GameScene gameScene = getGameScene();
        revealAllTiles();
        gameState.setGameOver(true);
        gameScene.stopTimer();
        gameScene.getResetButton().setDisable(true);
        getGameState().setPlayerScore(getGameScene().getScoreComponent().getScore());
        getGameState().setLevelsCompleted(getGameState().getLevelsCompleted());
        getGameState().setTimeRemaining(getGameScene().getTimerComponent().timeRemainingProperty().get());
        getGameState().setMinesSwept(calculateSweptMines());
        GameEventHelper.savePlayerStats(profileManager.getPlayer(), getGameState());
        UIEventHelper.handleNavigationWithDelay(stageManager.getMainStage(), sceneManager.getScene("FinishScene"), 3);
    }

    public int calculateSweptMines() {
        int sweptMinesCounter = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = tiles[row][col];
                if (tile.getTileModel().isMine() && tile.getTileModel().isFlagged()) {
                    sweptMinesCounter++;
                }
            }
        }
        return sweptMinesCounter;
    }

    public void resetGame(String difficulty, boolean newLevel) {
        if (getGameState().getGameOver()) {
            getGameScene().getResetButton().getStyleClass().clear();
            getGameScene().getResetButton().getStyleClass().add("game-reset-button-disabled");
            return;
        }
        getGameScene().resetGame(difficulty, newLevel);
    }

    public void revealAllTiles() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = tiles[row][col];
                if (!tile.getTileModel().isRevealed()) {
                    tile.getTileModel().setRevealed(true);
                    tile.updateView();
                }
            }
        }
    }

}
