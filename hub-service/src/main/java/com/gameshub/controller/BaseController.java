package com.gameshub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gameshub.model.AuthenticationManager;
import com.gameshub.model.Profile;
import com.gameshub.model.ProfileManager;
import com.gameshub.model.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class BaseController {

    private static final List<BaseController> registeredControllers = new ArrayList<>();

    protected static Stage mainStage;
    protected static Stage logoutStage;
    protected static Stage loginStage;
    protected static Stage changeUsernameStage;
    protected static Stage changePasswordStage;
    protected static Stage changeAvatarStage;
    protected ProfileManager profileManager;
    protected SceneManager sceneManager;
    protected AuthenticationManager authenticationManager;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    protected HBox toolbarHBox;

    public BaseController() {
        this.profileManager = ProfileManager.getInstance();
        this.authenticationManager = AuthenticationManager.getInstance();
        this.sceneManager = SceneManager.getInstance();
        registeredControllers.add(this);
    }

    // This method enables the toolbar to be draggable on whichever child controller has a toolbarHBox object
    protected void makeToolbarDraggable() {
        if (toolbarHBox != null) {
            toolbarHBox.setOnMousePressed(event -> {
                // Save the initial mouse offset
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            toolbarHBox.setOnMouseDragged(event -> {
                Window window = toolbarHBox.getScene().getWindow();
                if (window != null) {
                    // Dynamically move the window across the screen
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);

                    // Check if window is within bounds, else "bounce back"
                    checkWindowBounds(window);
                }
            });
        }
    }

    // This method ensures that the window doesn't go out of the screen bounds.
    protected void checkWindowBounds(Window window) {
        if (window == null)
            return;

        double windowWidth = window.getWidth();
        double windowHeight = window.getHeight();

        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        if (window.getX() < screenBounds.getMinX()) {
            window.setX(screenBounds.getMinX());
        }
        if (window.getX() + windowWidth > screenBounds.getMaxX()) {
            window.setX(screenBounds.getMaxX() - windowWidth);
        }

        if (window.getY() < screenBounds.getMinY()) {
            window.setY(screenBounds.getMinY());
        }
        if (window.getY() + windowHeight > screenBounds.getMaxY()) {
            window.setY(screenBounds.getMaxY() - windowHeight);
        }
    }

    // Method to update any controller with profile data
    public abstract void updateActiveProfileElements();

    public static void triggerUpdateActiveProfileElements() {
        for (BaseController controller : registeredControllers) {
            controller.updateActiveProfileElements();
        }
    }

    // This method does not save or set the preferences,
    // but will make the changes to the preference specific areas (theme will change
    // css, etc.)
    public void updateUserPreferences() {

        Profile profile = profileManager.getActiveProfile();

        if (profile == null) {
            System.out.println("No active profile. cannot apply preferences");
            return;
        }

        Map<String, Object> preferences = profile.getPreferences();
        System.out.println(preferences);

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

    // Method to reset the login stage back to the login scene (instead of showing
    // create profile, if selected)
    public void resetLogin() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameshub/login.fxml"));
        Parent root = loader.load();
        Scene loginScene = new Scene(root);
        loginStage.setScene(loginScene);
    }

}
