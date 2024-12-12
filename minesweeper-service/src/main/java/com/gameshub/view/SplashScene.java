package com.gameshub.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplashScene extends BaseScene {
   
    private VBox containerVBox;
    private VBox buttonContainerVBox;
    private Label titleLabel;
    private Button startButton;
    private Button optionsButton;
    private Button quitButton;
    
    public SplashScene(Stage stage) {

        super(stage);
        initialiseSceneUI();
        
    }

    @Override
    public void initialiseSceneUI() {

        containerVBox = new VBox(100);
        buttonContainerVBox = new VBox(20);
        titleLabel = new Label();
        startButton = new Button();
        optionsButton = new Button();
        quitButton = new Button();

        containerVBox.getStyleClass().add("splash-container");
        containerVBox.setMaxSize(800, 450);
        containerVBox.setAlignment(Pos.CENTER);
       
        buttonContainerVBox.setAlignment(Pos.CENTER);

        titleLabel.getStyleClass().add("splash-title-label");
        titleLabel.setText("Minesweeper");

        startButton.getStyleClass().add("menu-button");
        startButton.setText("Start");
        startButton.setPrefSize(180, 47);

        optionsButton.getStyleClass().add("menu-button");
        optionsButton.setText("Options");
        optionsButton.setPrefSize(180, 47);

        quitButton.getStyleClass().add("menu-button");
        quitButton.setText("Quit Game");
        quitButton.setPrefSize(180, 47);

        buttonContainerVBox.getChildren().addAll(startButton,optionsButton,quitButton);
        containerVBox.getChildren().addAll(titleLabel,buttonContainerVBox);
        getcontentBorderPane().setCenter(containerVBox);

    }

}
