// package com.gameshub.controller;

// import com.gameshub.model.Profile;

// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;

// public class CreateProfileController extends NavigationController {

//     @FXML
//     private Button exitButton;
//     @FXML
//     private Button createprofileButton;
//     @FXML
//     private TextField usernameField;
//     @FXML
//     private TextField passwordField;
//     @FXML
//     private Label validationLabel;
    

//     @FXML
//     public void initialize() {
//         exitButton.setOnAction(event -> handleExit(loginStage));
//         createprofileButton.setOnAction(event -> createProfile());
//     }

//     // Currently no elements to update
//     public void updateActiveProfileElements() {
//         System.out.println("This CreateProfileController does not currently update any active profile FXML elements");
//     }

//     // Method to handle profile creation
//     private void createProfile() {
        
//         String username = usernameField.getText().trim();
//         String password = passwordField.getText().trim();
//         String avatar = imageConversionService.convertToBase64("images/avatars/man1.jpg");
//         String preferences = "not implemented";

//         if (authenticationManager.authenticateProfileCreation(username, password, validationLabel)) {
//             // Create profile logic here + currently avatar & preferences not implemented 
//             Profile newProfile = new Profile(username,authenticationManager.hashPassword(password),avatar,preferences);
//             profileManager.addProfile(newProfile);
//             profileManager.setActiveProfile(newProfile);
//             System.out.println("Active Profile Set: " + newProfile.toString());
//             handleExit(loginStage);
//             openHub();
//         } 

//     }

// }

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

    // Avatar and preferences (consider implementing these in the future)
    private static final String DEFAULT_AVATAR = "/com/gameshub/images/avatars/man1.jpg";  // Default avatar path
    private static final String DEFAULT_PREFERENCES = "not implemented";

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
        
        // Ensure avatar path is valid, and convert it to Base64
        String avatarBase64 = imageConversionService.convertToBase64(DEFAULT_AVATAR);
        if (avatarBase64 == null) {
            validationLabel.setVisible(true);
            validationLabel.setText("Avatar conversion failed.");
            return;
        }

        String preferences = DEFAULT_PREFERENCES;

        // Validate the input fields
        if (username.isEmpty() || password.isEmpty()) {
            validationLabel.setVisible(true);
            validationLabel.setText("Username and password cannot be empty.");
            return;
        }

        // Authenticate profile creation and check for validation
        if (authenticationManager.authenticateProfileCreation(username, password, validationLabel)) {
            // Create the new profile with the avatar and preferences
            Profile newProfile = new Profile(username, authenticationManager.hashPassword(password), avatarBase64, preferences);

            // Add the profile to the profile manager and set as active profile
            profileManager.addProfile(newProfile);
            profileManager.setActiveProfile(newProfile);

            System.out.println("Active Profile Set: " + newProfile.toString());

            // Handle profile creation success
            handleExit(loginStage);
            openHub();  // Open the main hub after profile creation
        }
    }
}

