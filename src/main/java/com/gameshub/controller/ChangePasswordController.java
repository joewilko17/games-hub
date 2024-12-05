package com.gameshub.controller;

import com.gameshub.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangePasswordController extends NavigationController {
    
    @FXML
    private Button exitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmButton;
    @FXML
    private TextField currentPasswordField;
    @FXML
    private TextField newPasswordField;

    @FXML
    public void initialize() {

    }

    @Override
    public void updateActiveProfileElements() {
        // set text of currentUsernameField to display username
        Profile activeProfile = profileManager.getActiveProfile();
    }

}
