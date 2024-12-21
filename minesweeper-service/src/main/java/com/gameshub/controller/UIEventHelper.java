package com.gameshub.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIEventHelper {
    
    public static void handleExit(Stage stage) {
        stage.close();
    }

    public static void handleNavigation(Stage stage, Scene targetScene) {
        stage.setScene(targetScene);
    }
    
}
