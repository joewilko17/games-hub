package com.gameshub.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateProfileController extends NavigationController {
    
    @FXML
    private Button exitButton;
    @FXML
    private Button createprofileButton;

    // Method to handle profile creation
    private void createProfile() {
        // Profile creation validation
        // Create the profile and store in JSON
        handleExit(loginStage);
        openHub();
    }

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(loginStage));
        createprofileButton.setOnAction(event -> createProfile());
    }
}
