package com.gameshub.view;

import com.gameshub.view.components.ToolbarComponent;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseScene {

    private AnchorPane rootAnchorPane;
    private BorderPane contentBorderPane;
    private ToolbarComponent toolbarComponent;

    public BaseScene(Stage stage) {
        buildBaseGUI(stage);
    }

    public abstract void initialiseSceneUI();

    public Scene getScene() {
        return new Scene(rootAnchorPane, 800, 600); // Adjust dimensions as needed
    }

    public BorderPane getcontentBorderPane() {
        return contentBorderPane;
    }

    private void buildBaseGUI(Stage stage) {

        rootAnchorPane = new AnchorPane();
        contentBorderPane = new BorderPane();
        toolbarComponent = new ToolbarComponent();

        toolbarComponent.setTitle("Minesweeper");
        // Improvised shutdown method before EventHandler implementation
        toolbarComponent.setOnExitAction(() -> stage.close()); 

        
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
