package com.gameshub;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.gameshub.controller.SceneManager;



public class Main extends Application {

    private SceneManager sceneManager;

    @Override
    public void start(Stage mainStage) throws IOException {

        sceneManager = SceneManager.getInstance(mainStage);

        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setScene(sceneManager.getScene("SplashScene"));
        mainStage.show();

    }

    public static void main(String[] args) {

        launch();

    }

}