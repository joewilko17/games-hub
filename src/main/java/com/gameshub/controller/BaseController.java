package com.gameshub.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseController {

    protected static Stage mainStage;
    protected static Stage logoutStage;
    protected static Stage loginStage;

    // Initialize stages within main.java
    public static void initializeStages(Stage login, Stage main, Stage logout) {
        loginStage = login;
        mainStage = main;
        logoutStage = logout;
    }

    // Exit button logic
    public void handleExit(Stage stage) {
        if (stage != null) {
            stage.close();
        }
    }

    // Handle exiting of all existing stages
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

    public void resetLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameshub/login.fxml"));
        Parent root = loader.load();
        Scene loginScene = new Scene(root);
        loginStage.setScene(loginScene);
    }

}
