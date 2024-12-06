package com.gameshub.model;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javafx.scene.control.Label;

public class AuthenticationManager {

    private static AuthenticationManager instance;
    private final ProfileManager profileManager;

    private AuthenticationManager() {
        this.profileManager = ProfileManager.getInstance();
    }

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    // Method to check that a profile does exist, and verify the username is correct
    public boolean doesProfileExist(String username) {
        return profileManager.getProfileByUsername(username) != null;
    }

    // Method to verify that the entered password is equal to the password hash
    // associated with the profile
    public boolean verifyPassword(String passwordField, String passwordHash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(passwordField, passwordHash);
    }

    // Method to authenticate login (from fxml fields)
    public boolean authenticateLogin(String usernameField, String passwordField, Label validationLabel) {
        
        if (usernameField.isEmpty() || passwordField.isEmpty()) {
            System.out.println("username or password is empty");
            validationLabel.setVisible(true);
            validationLabel.setText("Both fields must have a entry.");
            return false;
        }

        if (doesProfileExist(usernameField)) {
            Profile profile = profileManager.getProfileByUsername(usernameField);
            if (profile != null) {
                if (verifyPassword(passwordField, profile.getPasswordHash())) {
                    return true;
                } else {
                    validationLabel.setVisible(true);
                    validationLabel.setText("Password is incorrect");
                }
            }
        }
        validationLabel.setVisible(true);
        validationLabel.setText("Username does not exist");
        return false;
    }

    // Method to authenticate profile creation (from fxml fields)
    public boolean authenticateProfileCreation(String usernameField, String passwordField, Label validationLabel) {

        if(!validateUsername(usernameField)) {
            validationLabel.setVisible(true);
            validationLabel.setText("Username is invalid");
            return false;
        }
        if(!validatePassword(passwordField)){
            validationLabel.setVisible(true);
            validationLabel.setText("Password is invalid");
            return false;
        }
        return true;
    }
    
    // Method to validate username for creation + updating
    public boolean validateUsername(String username) {

        // Check if username is unique
        if (doesProfileExist(username)) {
            return false;
        }

        // Check if username is between 3 and 20 characters in length
        if (username.length() < 3 || username.length() > 20) {
            return false;
        }

        // Check if username contains only alphanumeric characters
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            return false;
        }

        return true;
    }

    // Method to validate password creation
    public boolean validatePassword(String password) {

        // Check if the password meets the minimum length requirement
        if (password.length() < 8) {
            return false;
        }

        // Check if the password contains at least one number
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }

        // Check if the password contains at least one uppercase letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }

        // Check for if the password contains special characters
        if (!Pattern.compile("[!@#\\$%\\^&\\*]").matcher(password).find()) {
            return false;
        }

        return true;
    }

    // Method to encode the entered password if valid
    public String hashPassword(String password) {
        if (validatePassword(password)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.encode(password);
        } else {
            throw new IllegalArgumentException("Password is invalid.");
        }
    }
}
