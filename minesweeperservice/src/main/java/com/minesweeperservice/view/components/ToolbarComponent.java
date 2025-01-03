package com.minesweeperservice.view.components;

import com.minesweeperservice.controller.UIEventHelper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ToolbarComponent {

    private StackPane toolbarStackPane;
    private Label titleLabel;
    private Button exitButton;
    private ImageView exitIconImageView;
    private Image exitIconImage;

    public ToolbarComponent(Stage stage) {
        initialiseComponent(stage);
    }

    private void initialiseComponent(Stage stage) {

        toolbarStackPane = new StackPane();
        titleLabel = new Label();
        exitButton = new Button();
        exitIconImageView = new ImageView();
        exitIconImage = new Image(getClass().getResourceAsStream("/com/gameshub/icons/exit-icon.png"));

        toolbarStackPane.setPrefHeight(40);

        StackPane.setMargin(titleLabel, new Insets(0,0,0,15));

        exitIconImageView.setImage(exitIconImage);
        exitIconImageView.setPreserveRatio(true);
        exitIconImageView.setFitWidth(29);
        exitIconImageView.setFitHeight(41);

        exitButton.setPrefHeight(40);
        exitButton.setPrefHeight(45);
        exitButton.setGraphic(exitIconImageView);
        exitButton.setOnAction(_ -> UIEventHelper.handleExit(stage));
        
        StackPane.setAlignment(exitButton, Pos.CENTER_RIGHT);
        StackPane.setAlignment(titleLabel, Pos.CENTER_LEFT);
        toolbarStackPane.getChildren().addAll(titleLabel,exitButton);

    }

    public StackPane getToolbar() {
        return toolbarStackPane;
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }


}
