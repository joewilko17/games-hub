package com.gameshubservice.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogoutController extends NavigationController {

    @FXML
    private Button exitButton;
    @FXML
    private Button confirmlogoutButton;
    @FXML
    private Button cancelButton;

    // Method to handle logout
    private void handleLogout() throws Exception {
        // Logic to remove the current profile as active and return application to
        handleExitAll();
        resetLogin();
        openLogin();
    }

    private void handleCancel(Stage stage) {
        handleExit(stage);
    }

    @FXML
    public void initialize() {
        exitButton.setOnAction(_ -> handleCancel(logoutStage));
        confirmlogoutButton.setOnAction(_ -> {
            try {
                handleLogout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(_ -> handleCancel(logoutStage));
        makeToolbarDraggable();
    }

     // Currently no elements to update
     public void updateActiveProfileElements() {
        System.out.println("This LogoutController does not currently update any active profile FXML elements");
    }
}
