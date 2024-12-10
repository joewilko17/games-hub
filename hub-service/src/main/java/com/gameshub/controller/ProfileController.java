package com.gameshub.controller;

import java.io.File;

import com.gameshub.model.Profile;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProfileController extends NavigationController {

    // Navigation bar javaFX elements
    @FXML
    private Circle profileImageCircle;
    @FXML
    private Button exitButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button libraryButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button logoutButton;

    // Profile pane javaFX elements
    @FXML
    private Label usernameLabel;
    @FXML
    private Button changeUsernameButton;
    @FXML
    private Button changePasswordButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(mainStage));
        profileButton.setOnAction(event -> goToProfile(event));
        libraryButton.setOnAction(event -> goToLibrary(event));
        settingsButton.setOnAction(event -> goToSettings(event));
        logoutButton.setOnAction(event -> openLogout());
        changeUsernameButton.setOnAction(event -> openChangeUsernameDialog());
        changePasswordButton.setOnAction(event -> openChangePasswordDialog());
        profileImageCircle.setOnMouseClicked(event -> openChangeAvatarDialog());
        makeToolbarDraggable();
    }

    // Method to update active profile elements
    @Override
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        System.out.println("This is the active profile username as of now: " + activeProfile.getUsername());
        usernameLabel.setText(activeProfile.getUsername());
        File imageFile = new File(activeProfile.getAvatarImageURL());
        System.out.println(imageFile);
        Image img = new Image(imageFile.toURI().toString());
        profileImageCircle.setFill(new ImagePattern(img));
    }

    // Method to open username changing dialog window
    private void openChangeUsernameDialog() {
        try {
            changeUsernameStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameshub/changeusername.fxml"));
            Parent root = loader.load();
            Scene changeUsernameScene = new Scene(root);
            sceneManager.addScene("changeusername", root, loader.getController());
            changeUsernameStage.setScene(changeUsernameScene);
            changeUsernameStage.initStyle(StageStyle.UNDECORATED);
            changeUsernameStage.initOwner(mainStage);
            changeUsernameStage.initModality(Modality.APPLICATION_MODAL);
            sceneManager.updateScene(changeUsernameStage);
            changeUsernameStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to close password changing dialog window
    private void openChangePasswordDialog() {
        try {
        changePasswordStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameshub/changepassword.fxml"));
        Parent root = loader.load();
        Scene changePasswordScene = new Scene(root);
        sceneManager.addScene("changepassword", root, loader.getController());
        // ChangePasswordController controller = loader.getController();
        // controller.updateActiveProfileElements();
        changePasswordStage.setScene(changePasswordScene);
        changePasswordStage.initStyle(StageStyle.UNDECORATED);
        changePasswordStage.initOwner(mainStage);
        changePasswordStage.initModality(Modality.APPLICATION_MODAL);
        changePasswordStage.showAndWait();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
