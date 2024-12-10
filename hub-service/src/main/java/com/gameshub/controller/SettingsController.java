package com.gameshub.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.gameshub.model.Profile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SettingsController extends NavigationController {

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

    @FXML
    private ChoiceBox<String> themeChoiceBox;
    @FXML
    private ChoiceBox<String> defaultDifficultyChoiceBox;
    @FXML
    private CheckBox showHintsCheckBox;
    @FXML
    private Button saveChangesButton;

    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> handleExit(mainStage));
        profileButton.setOnAction(event -> goToProfile(event));
        libraryButton.setOnAction(event -> goToLibrary(event));
        settingsButton.setOnAction(event -> goToSettings(event));
        logoutButton.setOnAction(event -> openLogout());
        profileImageCircle.setOnMouseClicked(event -> openChangeAvatarDialog());
        saveChangesButton.setOnMouseClicked(event -> saveProfilePreferences(profileManager.getActiveProfile()));
        makeToolbarDraggable();
    }

    // Method to update active profile elements
    public void updateActiveProfileElements() {
        Profile activeProfile = profileManager.getActiveProfile();
        System.out.println(activeProfile);
        File imageFile = new File(activeProfile.getAvatarImageURL());
        Image img = new Image(imageFile.toURI().toString());
        profileImageCircle.setFill(new ImagePattern(img));
        setProfilePreferences(activeProfile);
    }

    private void setProfilePreferences(Profile profile) {
    
        Map<String, Object> preferences = profile.getPreferences();

        ObservableList<String> themeList = FXCollections.observableArrayList("dark", "light");
        themeChoiceBox.setItems(themeList);
        String currentTheme = (String) preferences.get("theme");
        themeChoiceBox.setValue(currentTheme);

        // potential to be a static final list, as will be accessed in many places
        ObservableList<String> difficultiesList = FXCollections.observableArrayList("easy", "medium", "hard");
        defaultDifficultyChoiceBox.setItems(difficultiesList);
        String currentDifficulty = (String) preferences.get("defaultDifficulty");
        defaultDifficultyChoiceBox.setValue(currentDifficulty);

        boolean currentShowHints = (boolean) preferences.get("showHints");
        showHintsCheckBox.setSelected(currentShowHints);

    }

    private void saveProfilePreferences(Profile profile) {
        
        String theme = themeChoiceBox.getValue();
        String difficulty = defaultDifficultyChoiceBox.getValue();
        boolean showHints = showHintsCheckBox.isSelected();

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("theme", theme);
        preferences.put("defaultDifficulty", difficulty);
        preferences.put("showHints", showHints);

        profile.setPreferences(preferences);
        profileManager.updateProfile(profile);
        updateUserPreferences();
    }
}
