package com.gameshub.controller;

import com.gameshub.model.ProfileManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseController {

    protected static Stage mainStage;
    protected static Stage logoutStage;
    protected static Stage loginStage;

    protected ProfileManager profileManager;


    public BaseController() {
        this.profileManager = ProfileManager.getInstance();
    }

    // Method to update any controller with profile data
    public abstract void updateActiveProfileElements();
    
    // Initialize stages within main.java
    public static void initializeStages(Stage login, Stage main, Stage logout) {
        loginStage = login;
        mainStage = main;
        logoutStage = logout;
    }

    // Method to handle exiting a given stage
    public void handleExit(Stage stage) {
        if (stage != null) {
            stage.close();
        }
    }

    // Method to handle exiting of all existing stages
    public void handleExitAll() {

        // Close the logoutStage if it exists
        if (logoutStage != null) {
            logoutStage.close();
        }
        // Close the loginStage if it exists
        if (loginStage != null) {
            loginStage.close();
        }
        // Close the mainStage if needed
        if (mainStage != null) {
            mainStage.close();
        }

    }

    // Method to reset the login stage back to the login scene (instead of showing create profile, if selected)
    public void resetLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameshub/login.fxml"));
        Parent root = loader.load();
        Scene loginScene = new Scene(root);
        loginStage.setScene(loginScene);
    }

}
