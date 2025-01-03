package com.gameshubservice.controller;

import com.gameshubservice.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangeUsernameController extends NavigationController {

    @FXML
    private Button exitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmButton;
    @FXML
    private TextField currentUsernameField;
    @FXML
    private TextField newUsernameField;
    @FXML
    private Label validationLabel;

    @FXML
    public void initialize() {
        // Need to implement dialog window opening + closing
        exitButton.setOnAction(_ -> handleExit(changeUsernameStage));
        cancelButton.setOnAction(_ -> handleExit(changeUsernameStage));
        confirmButton.setOnAction(_ -> updateUsername());
        makeToolbarDraggable();
        
    }

    @Override
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        currentUsernameField.setText(activeProfile.getUsername());
    }

    // Method to confirm new username is valid and save to JSON data file if so, before closing dialog window
    private void updateUsername() {
        String newUsername = newUsernameField.getText().trim();
        if (authenticationManager.validateUsername(newUsername)) {
            profileManager.getActiveProfile().setUsername(newUsername);
            profileManager.updateProfile(profileManager.getActiveProfile());
            BaseController.triggerUpdateActiveProfileElements();
            handleExit(changeUsernameStage);
            validationLabel.setVisible(false);
        } else {
            validationLabel.setText("Invalid new username");
            validationLabel.setVisible(true);
        }
        
        
    }
    
}
