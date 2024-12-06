package com.gameshub.controller;

import com.gameshub.model.Profile;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class LibraryController extends NavigationController {
    
    // Navigation bar javaFX UI elements
    @FXML
    private Circle profileImage;
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
        exitButton.setOnAction(event -> handleExit(mainStage));
        profileButton.setOnAction(event -> goToProfile(event));
        libraryButton.setOnAction(event -> goToLibrary(event));
        settingsButton.setOnAction(event -> goToSettings(event));
        logoutButton.setOnAction(event -> openLogout());
        gameButton1.setOnAction(event -> launchGame(event));
        gameButton2.setOnAction(event -> launchGame(event));
        gameButton3.setOnAction(event -> launchGame(event));
        gameButton4.setOnAction(event -> launchGame(event));
    }

    // Method to update active profile elements
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        System.out.println(activeProfile);
        // The image will be stored within the profile JSON data and will need to be converted before displaying
    }

    // Method to launch seperate game application
    private void launchGame(Event event) {
        Button clickedButton = (Button) event.getSource();
        System.out.println("launching this game: " + clickedButton.getText());
    }
}
