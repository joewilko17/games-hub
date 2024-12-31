package com.gameshubservice.controller;

import com.gameshubservice.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label validationLabel;

    @FXML
    public void initialize() {
        exitButton.setOnAction(_ -> handleExit(changePasswordStage));
        cancelButton.setOnAction(_ -> handleExit(changePasswordStage));
        confirmButton.setOnAction(_ -> updatePassword());
        makeToolbarDraggable();
    }

    @Override
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        System.out.println(activeProfile + " is currently not utilised in this ChangePasswordController");
    }

    // Method to confirm current password is valid user entry + new password does
    // not match the current,
    // before saving to JSON data file
    private void updatePassword() {
        String currentPassword = currentPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();

        // Check if fields are empty
        if (currentPassword.isEmpty() || newPassword.isEmpty()) {
            validationLabel.setVisible(true);
            validationLabel.setText("Both fields must have an entry");
            return;
        }

        // Verify the current password
        if (!authenticationManager.verifyPassword(currentPassword,
                profileManager.getActiveProfile().getPasswordHash())) {
            validationLabel.setVisible(true);
            validationLabel.setText("Entered password does not match current password");
            return;
        }

        // Validate the new password 
        if (!authenticationManager.validatePassword(newPassword)) {
            validationLabel.setVisible(true);
            validationLabel.setText("New password is invalid");
            return;
        }

        // Ensure it differs from the current password
        if (currentPassword.equals(newPassword)) {
            validationLabel.setVisible(true);
            validationLabel.setText("New password must be different from the current password");
            return;
        }

        // Update the password
        try {
            String hashedPassword = authenticationManager.hashPassword(newPassword);
            profileManager.getActiveProfile().setPasswordHash(hashedPassword);
            profileManager.updateProfile(profileManager.getActiveProfile());

            // Update the scene and close the change password window
            BaseController.triggerUpdateActiveProfileElements();
            handleExit(changePasswordStage);
            validationLabel.setVisible(false);
        } catch (Exception e) {
            validationLabel.setVisible(true);
            validationLabel.setText("Error hashing password: " + e.getMessage());
        }
    }

}
