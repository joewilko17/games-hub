package com.gameshub.controller;

import com.gameshub.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SettingsController extends NavigationController {

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

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(mainStage));
        profileButton.setOnAction(event -> goToProfile(event));
        libraryButton.setOnAction(event -> goToLibrary(event));
        settingsButton.setOnAction(event -> goToSettings(event));
        logoutButton.setOnAction(event -> openLogout());
    }

    // Method to update active profile elements
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        System.out.println(activeProfile);
        Image img = imageConversionService.convertToImage(activeProfile.getAvatarBase64());
        profileImageCircle.setFill(new ImagePattern(img));

    }
}