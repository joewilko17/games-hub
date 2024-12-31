package com.gameshubservice.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
            stage.show();
            BaseController.triggerUpdateActiveProfileElements();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void openChangeAvatarDialog() {
        try {
            changeAvatarStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameshubservice/changeavatar.fxml"));
            Parent root = loader.load();
            Scene changeAvatarScene = new Scene(root);
            sceneManager.addScene("changeavatar", root, loader.getController());
            changeAvatarStage.setScene(changeAvatarScene);
            changeAvatarStage.initStyle(StageStyle.UNDECORATED);
            changeAvatarStage.initOwner(mainStage);
            changeAvatarStage.initModality(Modality.APPLICATION_MODAL);
            changeAvatarStage.showAndWait();
            BaseController.triggerUpdateActiveProfileElements();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
