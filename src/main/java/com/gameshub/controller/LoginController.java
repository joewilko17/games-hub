package com.gameshub.controller;

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
        if (validateForm()) {
            handleExit(loginStage);
            openHub();
        }

        handleExit(loginStage);
        openHub();
    }

    private boolean validateForm() {
        return true;
    }

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(loginStage));
        loginButton.setOnAction(event -> handleLogin());
        createprofileButton.setOnAction(event -> goToCreateProfile(event));
    }
}
