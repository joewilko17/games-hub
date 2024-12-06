package com.gameshub.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class NavigationController extends BaseController {

    // Navigate to profile
    public void goToProfile(ActionEvent event) {
        navigateToScene("profile", event);
    }

    // Navigate to library
    public void goToLibrary(ActionEvent event) {
        navigateToScene("library", event);
    }

    // Navigate to settings
    public void goToSettings(ActionEvent event) {
        navigateToScene("settings", event);
    }

    // Navigate to create profile
    public void goToCreateProfile(ActionEvent event) {
        navigateToScene("createprofile", event);
    }

    // Navigate to login
    public void goToLogin(ActionEvent event) {
        navigateToScene("login", event);
    }

    // Navigate to logout in new window
    public void openLogout() {
        openStage(logoutStage);
    }

    // Navigate to login in new window
    public void openLogin() {
        openStage(loginStage);
    }

    // Navigate to hub in new window
    public void openHub() {
        openStage(mainStage);
    }

    private void navigateToScene(String sceneKey, ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            sceneManager.switchScene(sceneKey, stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open a new window
    private void openStage(Stage stage) {
        try {
            stage.setOnShown(event -> {
                BaseController.triggerUpdateActiveProfileElements();
            });

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
