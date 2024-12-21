package com.gameshub.view;

import com.gameshub.controller.UIEventHelper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameScene extends BaseScene {
    
    private VBox containerVBox;
    private VBox gameContentsContainerVBox;
    private HBox gameDisplayContainerHBox;
    private VBox gameGridContainerVBox;
    private Button resetButton;
    private Label scoreLabel;
    private Label timerLabel;

    private Label tempGameGridLabel; // Temp label to show where game will be implemented

    public GameScene(Stage stage) {

        super(stage);
        initialiseSceneUI();
        initialiseControlEvents(stage);
    }

    @Override
    public void initialiseSceneUI() {
        containerVBox = new VBox();
        gameContentsContainerVBox = new VBox(20);
        gameGridContainerVBox = new VBox();
        gameDisplayContainerHBox =  new HBox(50);
        resetButton = new Button("Reset");
        scoreLabel = new Label("Score: 0");
        timerLabel = new Label("Time Remaining: 10:00");
        tempGameGridLabel = new Label("Game grid will be here");

        containerVBox.getStyleClass().add("game-container");
        containerVBox.setMaxSize(700, 460);
        VBox.setMargin(resetButton, new Insets(20));
        
        resetButton.getStyleClass().add("game-reset-button");
        resetButton.setPrefSize(90, 27);

        gameContentsContainerVBox.setAlignment(Pos.CENTER);

        gameDisplayContainerHBox.getStyleClass().add("game-display-container");
        gameDisplayContainerHBox.setAlignment(Pos.CENTER);
        gameDisplayContainerHBox.setPrefHeight(50);

        // Refactor this label into a timer component class -> containing all update logic needed to countdown to zero
        timerLabel.getStyleClass().add("game-timer-component");
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setPrefSize(130, 40);

        // Refactor this label into a score component class -> containing all update logic to culminate score
        scoreLabel.getStyleClass().add("game-score-component");
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setPrefSize(130, 40);
        
        gameGridContainerVBox.getStyleClass().add("game-grid-container");
        gameGridContainerVBox.setAlignment(Pos.CENTER);
        gameGridContainerVBox.setPrefSize(400,300);
        gameGridContainerVBox.setMaxWidth(400);
        

        tempGameGridLabel.getStyleClass().add("setup-subtitle-label");

        gameDisplayContainerHBox.getChildren().addAll(timerLabel,scoreLabel);
        gameGridContainerVBox.getChildren().addAll(tempGameGridLabel);
        gameContentsContainerVBox.getChildren().addAll(gameDisplayContainerHBox,gameGridContainerVBox);
        containerVBox.getChildren().addAll(resetButton, gameContentsContainerVBox);
        getcontentBorderPane().setCenter(containerVBox);
        
    }

    @Override
    protected void initialiseControlEvents(Stage stage) {
        resetButton.setOnAction(_ -> System.out.println("Reset Game Called"));
        gameGridContainerVBox.setOnMouseClicked(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("FinishScene")));
    }
}
