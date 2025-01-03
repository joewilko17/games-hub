package com.gameshubservice.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.gameshubservice.model.Profile;

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

    // Default avatar and preferences for profile creation
    private static final String DEFAULT_AVATAR = "gameshubservice/images/avatars/1.jpg";

    @FXML
    public void initialize() {
        exitButton.setOnAction(_ -> handleExit(loginStage));
        createprofileButton.setOnAction(_ -> createProfile());
        makeToolbarDraggable();
    }

    // Currently no elements to update
    public void updateActiveProfileElements() {
        System.out.println("This CreateProfileController does not currently update any active profile FXML elements");
    }

    // Method to handle profile creation
    private void createProfile() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String avatar = DEFAULT_AVATAR;

        Map<String, Object> defaultPreferences = new HashMap<>();
        defaultPreferences.put("theme", "dark");
        defaultPreferences.put("defaultDifficulty", "easy");
        defaultPreferences.put("showHints", true);

        // Check that input fields are not empty before creating profile
        if (username.isEmpty() || password.isEmpty()) {
            validationLabel.setVisible(true);
            validationLabel.setText("Username and password cannot be empty.");
            return;
        }

        File imageFile = new File(avatar);
        if (imageFile.exists()) {
            System.out.println(imageFile);
        }

        // Authenticate profile creation and check for validation
        if (authenticationManager.authenticateProfileCreation(username, password, validationLabel)) {
            
            // Create the new profile with the avatar and preferences
            Profile newProfile = new Profile(
                    username,
                    authenticationManager.hashPassword(password),
                    avatar,
                    defaultPreferences);

            // Add the profile to the profile manager and set as active profile
            profileManager.addProfile(newProfile);
            profileManager.setActiveProfile(newProfile);

            System.out.println("Active Profile Set: " + newProfile.toString());

            // Handle profile creation success
            handleExit(loginStage);
            openHub(); // Open the main hub after profile creation
        }
    }
}
