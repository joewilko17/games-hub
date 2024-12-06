package com.gameshub.controller;

import com.gameshub.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateProfileController extends NavigationController {
    
    @FXML
    private Button exitButton;
    @FXML
    private Button createprofileButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label validationLabel;
    

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(loginStage));
        createprofileButton.setOnAction(event -> createProfile());
    }

    // Currently no elements to update
    public void updateActiveProfileElements() {
        System.out.println("This CreateProfileController does not currently update any active profile FXML elements");
    }

    // Method to handle profile creation
    private void createProfile() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String avatar = "not implemented";
        String preferences = "not implemented";

        if (authenticationManager.authenticateProfileCreation(username, password, validationLabel)) {
            // Create profile logic here + currently avatar & preferences not implemented 
            Profile newProfile = new Profile(username,authenticationManager.hashPassword(password),avatar,preferences);
            profileManager.addProfile(newProfile);
            profileManager.setActiveProfile(newProfile);
            System.out.println("Active Profile Set: " + newProfile.toString());
            handleExit(loginStage);
            openHub();
        } 

    }

}
