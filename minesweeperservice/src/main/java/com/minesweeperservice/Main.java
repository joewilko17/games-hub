package com.minesweeperservice;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.minesweeperservice.controller.ProfileManager;
import com.minesweeperservice.controller.SceneManager;
import com.minesweeperservice.controller.StageManager;

public class Main extends Application {

    public static String currentProfileName;
    private StageManager stageManager;
    private SceneManager sceneManager;
    private ProfileManager profileManager;

    @Override
    public void start(Stage mainStage) throws IOException {
        List<String> params = getParameters().getRaw();
        System.out.println(params.toString());
        if (!params.isEmpty()) {
            currentProfileName = params.get(0); // Get the first argument passed
            System.out.println("Current profile name: " + currentProfileName);
        }
        currentProfileName = "Joe"; // TEST LINE
        profileManager = ProfileManager.getInstance(currentProfileName);
        stageManager = StageManager.getInstance();
        stageManager.setMainStage(mainStage);
        sceneManager = SceneManager.getInstance(mainStage);
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setScene(sceneManager.getScene("SplashScene"));
        mainStage.show();
        System.out.println("Welcome to Minesweeper " + profileManager.getPlayer().getPlayerName());
    }

    public static void main(String[] args) {
        System.out.println("Arguments received: " + Arrays.toString(args));
        launch(args);
    }

}