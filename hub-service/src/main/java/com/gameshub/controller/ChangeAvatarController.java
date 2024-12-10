package com.gameshub.controller;

import java.io.File;

import com.gameshub.model.Profile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ChangeAvatarController extends NavigationController {

    private String selectedAvatarImageURL;

    @FXML
    private Button exitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Label validationLabel;

    @FXML
    private Circle avatarImageCircle1;
    @FXML
    private Circle avatarImageCircle2;
    @FXML
    private Circle avatarImageCircle3;
    @FXML
    private Circle avatarImageCircle4;
    @FXML
    private Circle avatarImageCircle5;
    @FXML
    private Circle avatarImageCircle6;
    @FXML
    private Circle avatarImageCircle7;
    @FXML
    private Circle avatarImageCircle8;

    @FXML
    public void initialize() {
        Profile activeProfile = profileManager.getActiveProfile();
        Circle[] avatarCircles = {
                avatarImageCircle1, avatarImageCircle2, avatarImageCircle3, avatarImageCircle4,
                avatarImageCircle5, avatarImageCircle6, avatarImageCircle7, avatarImageCircle8
        };

        exitButton.setOnAction(event -> handleExit(changeAvatarStage));
        cancelButton.setOnAction(event -> handleExit(changeAvatarStage));
        confirmButton.setOnAction(event -> updateAvatar(selectedAvatarImageURL));
        loadAvatarImages(avatarCircles);
        setSelectedAvatarImage(activeProfile.getAvatarImageURL(), avatarCircles);

        for (Circle circle : avatarCircles) {
            circle.setOnMouseClicked(event -> selectAvatarImage(circle, avatarCircles));
        }

        makeToolbarDraggable();
    }

    @Override
    public void updateActiveProfileElements() {
        System.out.println("This ChangeAvatarController does not update any active profile FXML elements");
    }

    public void loadAvatarImages(Circle[] avatarCircles) {

        String avatarsFolderPath = "hub-service/images/avatars/";

        for (int i = 0; i < avatarCircles.length; i++) {
            try {
                String imagePath = avatarsFolderPath + (i + 1) + ".jpg"; // Assuming files are named 1.jpg, 2.jpg, etc.
                File imageFile = new File(imagePath);

                if (!imageFile.exists()) {
                    System.err.println("Image not found: " + imagePath);
                    avatarCircles[i].setFill(Color.GRAY); // Fallback color
                    continue;
                }

                Image img = new Image(imageFile.toURI().toString());

                avatarCircles[i].setFill(new ImagePattern(img));

            } catch (Exception e) {
                System.err.println("Failed to load image for Circle " + (i + 1) + ": " + e.getMessage());
                avatarCircles[i].setFill(Color.GRAY);
            }
        }
    }

    private void setSelectedAvatarImage(String avatarImageURL, Circle[] avatarCircles) {
        for (int i = 0; i < avatarCircles.length; i++) {
            Circle circle = avatarCircles[i];
            ImagePattern fillPattern = (ImagePattern) circle.getFill();

            if (fillPattern != null) {
                Image circleImage = fillPattern.getImage();
                String circleImagePath = circleImage.getUrl().replace("file:/", "").replace("%20", "");
                circleImagePath = circleImagePath.substring(circleImagePath.indexOf("hub-service"));
                System.out.println(circleImagePath);
                System.out.println(avatarImageURL);
                // Check if the circle's image matches the active profile's avatar image
                if (circleImagePath.equals(avatarImageURL)) {
                    // Set a thick blue border for the selected circle
                    System.out.print(circle);
                    circle.setStroke(Color.BLUE);
                    circle.setStrokeWidth(5.0);
                } else {
                    // Reset the border for non-selected circles
                    circle.setStroke(Color.TRANSPARENT);
                    circle.setStrokeWidth(0.0);
                }
            }
        }
    }

    // Method called on to select an avatar image and deselect the previous
    private void selectAvatarImage(Circle circleToSelect, Circle[] avatarCircles) {
        // Deselect all circles by resetting their borders
        for (Circle circle : avatarCircles) {
            circle.setStroke(Color.TRANSPARENT);
            circle.setStrokeWidth(0.0);
        }

        // Select the clicked circle with a blue border
        circleToSelect.setStroke(Color.BLUE);
        circleToSelect.setStrokeWidth(5.0);

        // Update the URL of the selected avatar image
        ImagePattern pattern = (ImagePattern) circleToSelect.getFill();

        if (pattern != null) {
            String fullImageURL = pattern.getImage().getUrl();
            selectedAvatarImageURL = fullImageURL.substring(fullImageURL.indexOf("hub-service/"));

            System.out.println("Selected avatar image URL updated to: " + selectedAvatarImageURL);
        } else {
            System.err.println("Could not find the pattern image in the clicked circle.");
        }
    }

    // Method that gets the currently selected profile picture and sets it as the
    private void updateAvatar(String selectedAvatarImageURL) {
        try {
            profileManager.getActiveProfile().setAvatarImageURL(selectedAvatarImageURL);
            profileManager.updateProfile(profileManager.getActiveProfile());
            BaseController.triggerUpdateActiveProfileElements();
            handleExit(changeAvatarStage);
            validationLabel.setVisible(false);
        } catch (Exception e) {
            validationLabel.setVisible(true);
            validationLabel.setText("Please select an avatar image");
        }
    }
}
