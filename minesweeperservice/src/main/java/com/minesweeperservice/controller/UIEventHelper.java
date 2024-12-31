package com.minesweeperservice.controller;

import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIEventHelper {

    public static void handleExit(Stage stage) {
        stage.close();
    }

    public static void handleNavigation(Stage stage, Scene targetScene) {
        stage.setScene(targetScene);
    }

    public static void handleNavigationWithDelay(Stage stage, Scene targetScene, double delayInSeconds) {
        PauseTransition pause = new PauseTransition(Duration.seconds(delayInSeconds));
        pause.setOnFinished(_ -> stage.setScene(targetScene));
        pause.play();
    }

}
