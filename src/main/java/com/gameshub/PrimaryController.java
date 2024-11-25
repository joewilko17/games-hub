package com.gameshub;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void switchToProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private void switchToLibrary() throws IOException {
        App.setRoot("library");
    }

    @FXML
    private void showLogoutScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/logout.fxml"));
            Parent root = loader.load();

            Stage logoutStage = new Stage();
            logoutStage.initModality(Modality.APPLICATION_MODAL);
            logoutStage.setTitle("Confirm Logout");

            Scene scene = new Scene(root);
            logoutStage.setScene(scene);
            logoutStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("failed to load the logout.fmxl file");
        }
    }

}
