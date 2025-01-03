package com.minesweeperservice.view;

import com.minesweeperservice.controller.GameController;
import com.minesweeperservice.controller.ProfileManager;
import com.minesweeperservice.controller.SceneManager;
import com.minesweeperservice.view.components.ToolbarComponent;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseScene {

    protected SceneManager sceneManager;
    protected GameController gameController;
    protected ProfileManager profileManager;
    
    protected Scene scene;

    private AnchorPane rootAnchorPane;
    private BorderPane contentBorderPane;
    private ToolbarComponent toolbarComponent;

    public BaseScene(Stage stage) {
        
        sceneManager = SceneManager.getInstance(stage);
        gameController = GameController.getInstance();
        profileManager = ProfileManager.getInstance();
        buildBaseGUI(stage);
        
    }

    protected abstract void initialiseSceneUI();

    protected abstract void initialiseControlEvents(Stage stage);

    public Scene createScene(int width, int height) {
        scene = new Scene(rootAnchorPane, width, height);
        return scene;
    }

    public Scene getScene() {
        return scene;
    }

    public BorderPane getcontentBorderPane() {
        return contentBorderPane;
    }

    private void buildBaseGUI(Stage stage) {

        rootAnchorPane = new AnchorPane();
        contentBorderPane = new BorderPane();
        toolbarComponent = new ToolbarComponent(stage);

        toolbarComponent.setTitle("Minesweeper");
    
        rootAnchorPane.getChildren().add(contentBorderPane);
        AnchorPane.setTopAnchor(contentBorderPane, 0.0);
        AnchorPane.setBottomAnchor(contentBorderPane, 0.0);
        AnchorPane.setLeftAnchor(contentBorderPane, 0.0);
        AnchorPane.setRightAnchor(contentBorderPane, 0.0);

        contentBorderPane.setTop(toolbarComponent.getToolbar());
        contentBorderPane.setMaxHeight(800);
        contentBorderPane.setMaxWidth(600);

        rootAnchorPane.getStylesheets().add(getClass().getResource("/com/gameshub/styles/dark.css").toExternalForm());
        contentBorderPane.getStyleClass().add("content-border-pane");
        toolbarComponent.getToolbar().getStyleClass().add("toolbar-component");

    }

}
