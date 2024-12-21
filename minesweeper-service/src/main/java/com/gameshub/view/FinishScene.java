package com.gameshub.view;

import com.gameshub.controller.UIEventHelper;

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
    }

    @Override
    public void initialiseSceneUI() {
        containerVBox = new VBox(20);
        finishContentsContainerVBox = new VBox(10);
        finishStatisticsDisplayContainerVBox = new VBox(10);
        buttonsContainerHBox = new HBox(20);
        gameOverTitleLabel = new Label("Game Over");
        scoreLabel = new Label("Score: 0");
        levelsCompletedLabel = new Label("Levels Completed: 0");
        timeRemainingLabel = new Label("Time Remaining: 10:00");
        minesSweptLabel = new Label("Mines Swept: 0");
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

        finishStatisticsDisplayContainerVBox.getChildren().addAll(scoreLabel,levelsCompletedLabel,timeRemainingLabel,minesSweptLabel);
        finishContentsContainerVBox.getChildren().addAll(gameOverTitleLabel,finishStatisticsDisplayContainerVBox);
        buttonsContainerHBox.getChildren().addAll(mainMenuButton,startAgainButton);
        containerVBox.getChildren().addAll(finishContentsContainerVBox,buttonsContainerHBox);
        getcontentBorderPane().setCenter(containerVBox);
    }

    @Override
    protected void initialiseControlEvents(Stage stage) {
       startAgainButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("GameScene")));
       mainMenuButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("SplashScene")));
        
    }
}
