package com.gameshub.controller;

import com.gameshub.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends NavigationController {
    
    @FXML
    private Button exitButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button createprofileButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label validationLabel;


    // Method to handle login + validation needed on existing profile -> before going to hub
    private void handleLogin() {
        
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        
        if (validateFields(username,password)) {
            Profile activeProfile = profileManager.getProfileByUsername(username);
            profileManager.setActiveProfile(activeProfile);
            System.out.println("Active Profile Set: " + activeProfile);
            handleExit(loginStage);
            BaseController.triggerUpdateActiveProfileElements();
            openHub();
        }

    }

    private boolean validateFields(String usernameField, String passwordField) {

        if (!authenticationManager.authenticateLogin(usernameField, passwordField, validationLabel)) {
            return false;
        }
        validationLabel.setVisible(false);
        return true;

    }

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(loginStage));
        loginButton.setOnAction(event -> handleLogin());
        createprofileButton.setOnAction(event -> goToCreateProfile(event));
    }

    // Currently no elements to update
    public void updateActiveProfileElements() {
        System.out.println("This LoginController does not currently update any active profile FXML elements");
    }
}
