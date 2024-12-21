package com.gameshub.view;

import com.gameshub.controller.UIEventHelper;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PauseScene extends BaseScene {

    private VBox containerVBox;
    private HBox buttonsContainerHBox;
    private Label pausedGameLabel;
    private Button resumeButton;
    private Button mainMenuButton;
    
    public PauseScene(Stage stage) {

        super(stage);
        initialiseSceneUI();
        initialiseControlEvents(stage);
    }

    @Override
    public void initialiseSceneUI() {

        containerVBox = new VBox(20);
        buttonsContainerHBox = new HBox(20);
        pausedGameLabel = new Label("Game is currently paused");
        resumeButton = new Button("Resume");
        mainMenuButton = new Button("Main Menu");

        containerVBox.setAlignment(Pos.CENTER);

        pausedGameLabel.getStyleClass().add("pause-title-label");

        buttonsContainerHBox.setAlignment(Pos.CENTER);

        resumeButton.getStyleClass().add("menu-button");
        resumeButton.setPrefSize(90, 27);
        mainMenuButton.getStyleClass().add("menu-button");
        mainMenuButton.setPrefSize(90, 27);

        buttonsContainerHBox.getChildren().addAll(resumeButton,mainMenuButton);
        containerVBox.getChildren().addAll(pausedGameLabel,buttonsContainerHBox);
        getcontentBorderPane().setCenter(containerVBox);
        
    }

    @Override
    protected void initialiseControlEvents(Stage stage) {
        // Refactor with resume() method that will resume the games current progress after a pause has occured
        resumeButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("GameScene")));
        mainMenuButton.setOnAction(_ -> UIEventHelper.handleNavigation(stage, sceneManager.getScene("SplashScene")));
    }
}
