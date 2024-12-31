package com.gameshubservice.controller;
import java.io.File;

import com.gameshubservice.model.GameLauncher;
import com.gameshubservice.model.Profile;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class LibraryController extends NavigationController {

    // Navigation bar javaFX UI elements
    @FXML
    private Circle profileImageCircle;
    @FXML
    private Button exitButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button libraryButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button logoutButton;

    // Library pane javaFX UI elements
    @FXML
    private Button gameButton1;
    @FXML
    private Button gameButton2;
    @FXML
    private Button gameButton3;
    @FXML
    private Button gameButton4;

    @FXML
    public void initialize() {
        exitButton.setOnAction(_ -> handleExit(mainStage));
        profileButton.setOnAction(event -> goToProfile(event));
        libraryButton.setOnAction(event -> goToLibrary(event));
        settingsButton.setOnAction(event -> goToSettings(event));
        logoutButton.setOnAction(_ -> openLogout());
        gameButton1.setOnAction(event -> launchGame(event));
        gameButton2.setOnAction(event -> launchGame(event));
        gameButton3.setOnAction(event -> launchGame(event));
        gameButton4.setOnAction(event -> launchGame(event));
        profileImageCircle.setOnMouseClicked(_ -> openChangeAvatarDialog());
        makeToolbarDraggable();
    }

    // Method to update active profile elements
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        System.out.println("this is libraryprofile");
        File imageFile = new File(activeProfile.getAvatarImageURL());
        Image img = new Image(imageFile.toURI().toString());
        System.out.print("setting profileCircleImage to:" + img.getUrl());
        profileImageCircle.setFill(new ImagePattern(img));
        System.out.print(profileImageCircle.getFill());
    }

    // Method to launch seperate game application
    private void launchGame(Event event) {
        Button clickedButton = (Button) event.getSource();
        System.out.println("launching this game: " + clickedButton.getText());
        String gameToRun = clickedButton.getText();
        String currentProfileName = profileManager.getActiveProfile().getUsername();
        switch (gameToRun) {
            case "Minesweeper":
                GameLauncher.launchMinesweeperService(currentProfileName);
                
        }
    }
}
