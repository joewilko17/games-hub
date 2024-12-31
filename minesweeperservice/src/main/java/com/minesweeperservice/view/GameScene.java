package com.minesweeperservice.view;

import com.minesweeperservice.controller.GameController;
import com.minesweeperservice.controller.UIEventHelper;
import com.minesweeperservice.view.components.ScoreComponent;
import com.minesweeperservice.view.components.TimerComponent;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScene extends BaseScene {

    private VBox containerVBox;
    private VBox gameContentsContainerVBox;
    private HBox gameDisplayContainerHBox;
    private VBox gameGridContainerVBox;
    private Button resetButton;
    private Button backButton;
    private Label timerLabel;
    private ScoreComponent scoreComponent;
    private TimerComponent timerComponent;
    private Label levelsCompletedLabel;
    private Label flagCountLabel;

    public GameScene(Stage stage) {
        super(stage);
        initialiseSceneUI();
        initialiseControlEvents(stage);
        resetGame(gameController.getSelectedDifficulty(), false);
        gameController.setGameScene(this);
    }

    @Override
    public void initialiseSceneUI() {
        containerVBox = new VBox();
        gameContentsContainerVBox = new VBox(20);
        gameGridContainerVBox = new VBox();
        gameDisplayContainerHBox = new HBox(40);
        resetButton = new Button("Reset");
        backButton = new Button("Back to menu");
        timerLabel = new Label("Time Remaining: 10:00");
        levelsCompletedLabel = new Label("Level: " + 1);
        flagCountLabel = new Label();

        containerVBox.getStyleClass().add("game-container");
        containerVBox.setMaxSize(700, 460);
        VBox.setMargin(resetButton, new Insets(20));
        VBox.setMargin(backButton, new Insets(20,20,0,20));

        resetButton.setAlignment(Pos.CENTER);
        resetButton.getStyleClass().add("game-reset-button");
        resetButton.setPrefSize(90, 40);

        backButton.setAlignment(Pos.CENTER);
        backButton.getStyleClass().add("game-reset-button");
        backButton.setPrefSize(90, 40);

        gameContentsContainerVBox.setAlignment(Pos.CENTER);

        gameGridContainerVBox.setAlignment(Pos.CENTER);

        gameDisplayContainerHBox.getStyleClass().add("game-display-container");
        gameDisplayContainerHBox.setAlignment(Pos.CENTER);
        gameDisplayContainerHBox.setPrefHeight(50);

        levelsCompletedLabel.setAlignment(Pos.CENTER);
        levelsCompletedLabel.getStyleClass().add("game-display-component");
        levelsCompletedLabel.setPrefSize(65, 40);


        flagCountLabel.setAlignment(Pos.CENTER);
        flagCountLabel.getStyleClass().add("game-display-component");
        flagCountLabel.setPrefSize(80,40);
        // Refactor this label into a timer component class -> containing all update
        // logic needed to countdown to zero

        initialiseTimer();
        timerLabel.getStyleClass().add("game-timer-component");
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setPrefSize(130, 40);

        // Refactor this label into a score component class -> containing all update
        // logic to culminate score
        scoreComponent = new ScoreComponent(0);
        Label scoreLabel = scoreComponent.getScoreComponent();

        gameGridContainerVBox.getStyleClass().add("game-grid-container");
        gameGridContainerVBox.setAlignment(Pos.CENTER);
        gameGridContainerVBox.setPrefSize(400, 300);
        gameGridContainerVBox.setMaxWidth(400);


        gameDisplayContainerHBox.getChildren().addAll(timerLabel, levelsCompletedLabel, flagCountLabel, scoreLabel);
        // gameGridContainerVBox.getChildren().addAll(tempGameGridLabel);
        gameContentsContainerVBox.getChildren().addAll(gameDisplayContainerHBox, gameGridContainerVBox);
        containerVBox.getChildren().addAll(backButton,resetButton, gameContentsContainerVBox);
        getcontentBorderPane().setCenter(containerVBox);

    }

    @Override
    protected void initialiseControlEvents(Stage stage) {
        resetButton.setOnAction(_ -> resetGame(gameController.getSelectedDifficulty(), false));
        backButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("SetupScene")));
        // gameGridContainerVBox.setOnMouseClicked(_ ->
        // UIEventHelper.handleNavigation(stage, sceneManager.getScene("FinishScene")));
    }

    private void initialiseTimer() {
        timerComponent = new TimerComponent(120, () -> gameOver());

        timerLabel.textProperty().bind(
                Bindings.createStringBinding(() -> {
                    int time = timerComponent.timeRemainingProperty().get();
                    int minutes = time / 60;
                    int seconds = time % 60;
                    return String.format("Time Remaining: %2d:%02d", minutes, seconds);
                }, timerComponent.timeRemainingProperty()));
    }

    public void startTimer() {
        timerComponent.start();
    }

    public void stopTimer() {
        timerComponent.stop();
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public TimerComponent getTimerComponent() {
        return timerComponent;
    }

    private void gameOver() {
        gameController.gameOver();
    }

    public Button getResetButton() {
        return resetButton;
    }

    public ScoreComponent getScoreComponent() {
        return scoreComponent;
    }

    public Label getLevelsCompletedLabel() {
        return levelsCompletedLabel;
    }

    public Label getFlagCountLabel() {
        return flagCountLabel;
    }

    public void disableResetButton(GameController gameController) {
        System.out.println("Disabling reset button");

        if (gameController.getGameState().getGameOver()) {
            resetButton.getStyleClass().clear();
            resetButton.getStyleClass().add("game-reset-button-disabled");
            return;
        }
    }

    public void resetGame(String difficulty, boolean newLevel) {
        gameGridContainerVBox.getChildren().clear();
        gameController.setFirstClick(true);
        if (newLevel) {
            initialiseGame(difficulty);
        } else {
            if (gameController.getGameState() != null) {
                int score = 0;
                int timeRemaining = 120;
                gameController.getGameState().setPlayerScore(score);
                gameController.getGameState().setTimeRemaining(timeRemaining);
                getTimerComponent().resetTimer(timeRemaining);
                getScoreComponent().resetScore();
            }
            initialiseGame(difficulty);
        }
    }

    public void initialiseGame(String difficulty) {

        int rows = 0;
        int cols = 0;
        int maxMines = 0;

        switch (difficulty) {
            case "easy":
                rows = 9;
                cols = 9;
                maxMines = 10;
                break;
            case "medium":
                rows = 16;
                cols = 16;
                maxMines = 40;
                break;
            case "hard":
                rows = 16;
                cols = 24;
                maxMines = 50;
                break;
            default:
                throw new IllegalArgumentException("Unknown Difficulty: " + difficulty);
        }

        System.out.println("Difficulty: " + difficulty);
        System.out.println("Rows: " + rows + ", Cols: " + cols + ", Mines: " + maxMines);
        flagCountLabel.setText("Flags: " + 0 + "/" + maxMines);
        gameController.createGrid(rows, cols, maxMines);
        GridPane gameGrid = gameController.getGameGrid();

        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setMinSize(400, 300);
        gameGrid.setMaxSize(400, 300);
        gameGridContainerVBox.getChildren().addAll(gameGrid);
        resetButton.getStyleClass().clear();
        resetButton.getStyleClass().add("game-reset-button");
    }
}
