package com.gameshub.controller;

import java.util.ArrayList;
import java.util.List;

import com.gameshub.model.AuthenticationManager;
import com.gameshub.model.ImageConversionService;
import com.gameshub.model.ProfileManager;
import com.gameshub.model.SceneManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseController {

    private static final List<BaseController> registeredControllers = new ArrayList<>();

    protected static Stage mainStage;
    protected static Stage logoutStage;
    protected static Stage loginStage;
    protected static Stage changeUsernameStage;
    protected static Stage changePasswordStage;

    protected ProfileManager profileManager;
    protected SceneManager sceneManager;
    protected AuthenticationManager authenticationManager;
    protected ImageConversionService imageConversionService;

    public BaseController() {
        this.profileManager = ProfileManager.getInstance();
        this.authenticationManager = AuthenticationManager.getInstance();
        this.sceneManager = SceneManager.getInstance();
        this.imageConversionService = ImageConversionService.getInstance();
        registeredControllers.add(this);
    }

    // Method to update any controller with profile data
    public abstract void updateActiveProfileElements();

    public static void triggerUpdateActiveProfileElements() {
        for (BaseController controller : registeredControllers) {
            controller.updateActiveProfileElements();
        }
    }
    
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
