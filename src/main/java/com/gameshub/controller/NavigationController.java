package com.gameshub.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class NavigationController extends BaseController {

    // Navigate to profile
    public void goToProfile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            switchScene("/com/gameshub/profile.fxml", stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Navigate to library
    public void goToLibrary(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            switchScene("/com/gameshub/library.fxml", stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Navigate to settings
    public void goToSettings(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            switchScene("/com/gameshub/settings.fxml", stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Navigate to create profile
    public void goToCreateProfile(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            switchScene("/com/gameshub/createprofile.fxml", stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToLogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            switchScene("/com/gameshub/login.fxml", stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    // Method to open switch the current scene
    private void switchScene(String fxmlFile, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open a new window
    private void openStage(Stage stage) {
        try {
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
