package com.minesweeperservice.view;

import com.minesweeperservice.controller.UIEventHelper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinishScene extends BaseScene {

    private VBox containerVBox;
    private VBox finishContentsContainerVBox;
    private VBox finishStatisticsDisplayContainerVBox;
    private HBox buttonsContainerHBox;
    private Label gameOverTitleLabel;
    private Label scoreLabel;
    private Label levelsCompletedLabel;
    private Label timeRemainingLabel;
    private Label minesSweptLabel;
    private Button mainMenuButton;
    private Button startAgainButton;

    public FinishScene(Stage stage) {

        super(stage);
        initialiseSceneUI();
        initialiseControlEvents(stage);
        updatePlayerData();
    }

    @Override
    public void initialiseSceneUI() {
        containerVBox = new VBox(20);
        finishContentsContainerVBox = new VBox(10);
        finishStatisticsDisplayContainerVBox = new VBox(10);
        buttonsContainerHBox = new HBox(20);
        gameOverTitleLabel = new Label("Game Over");
        scoreLabel = new Label("Score: " + gameController.getGameState().getPlayerScore());
        levelsCompletedLabel = new Label("Levels Completed:" + gameController.getGameState().getLevelsCompleted());
        timeRemainingLabel = new Label(displayTimeRemaining());
        minesSweptLabel = new Label("Mines Swept: " + gameController.getGameState().getMinesSwept());
        mainMenuButton = new Button("Main Menu");
        startAgainButton = new Button("Start Again");

        containerVBox.getStyleClass().add("finish-container");
        containerVBox.setMaxSize(700, 460);
        containerVBox.setAlignment(Pos.CENTER);

        finishContentsContainerVBox.getStyleClass().add("finish-menu-container");
        finishContentsContainerVBox.setMaxSize(380, 300);
        finishContentsContainerVBox.setAlignment(Pos.CENTER);
        VBox.setMargin(finishStatisticsDisplayContainerVBox, new Insets(20));

        gameOverTitleLabel.getStyleClass().add("finish-title-label");
        scoreLabel.getStyleClass().add("finish-stat-label");
        levelsCompletedLabel.getStyleClass().add("finish-stat-label");
        timeRemainingLabel.getStyleClass().add("finish-stat-label");
        minesSweptLabel.getStyleClass().add("finish-stat-label");

        buttonsContainerHBox.setAlignment(Pos.CENTER);

        mainMenuButton.getStyleClass().add("menu-button");
        mainMenuButton.setPrefSize(180, 27);

        startAgainButton.getStyleClass().add("menu-button");
        startAgainButton.setPrefSize(180, 27);

        finishStatisticsDisplayContainerVBox.getChildren().addAll(timeRemainingLabel, scoreLabel, levelsCompletedLabel, 
                minesSweptLabel);
        finishContentsContainerVBox.getChildren().addAll(gameOverTitleLabel, finishStatisticsDisplayContainerVBox);
        buttonsContainerHBox.getChildren().addAll(mainMenuButton, startAgainButton);
        containerVBox.getChildren().addAll(finishContentsContainerVBox, buttonsContainerHBox);
        getcontentBorderPane().setCenter(containerVBox);
    }

    @Override
    protected void initialiseControlEvents(Stage stage) {
        startAgainButton.setOnAction(_ -> restartGame(stage, gameController.getSelectedDifficulty()));
        mainMenuButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("SplashScene")));

    }

    private String displayTimeRemaining() {
        int time = gameController.getGameState().getTimeRemaining();
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("Time Remaining: %2d:%02d", minutes, seconds);
    }

    private void updatePlayerData() {
        // Save gameState into player object being managed by profileManager
        profileManager.updatePlayerData();
    }

    private void restartGame(Stage stage, String difficulty) {
        gameController.resetGame(difficulty, false);
        UIEventHelper.handleNavigation(stage, sceneManager.getScene("GameScene"));
    }
}
