package com.gameshub;

import com.gameshub.controller.BaseController;
import com.gameshub.model.SceneManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    
    private static Stage mainStage;
    private static Stage loginStage;
    private static Stage logoutStage;

    private SceneManager sceneManager;

    @Override
    public void start(Stage primaryStage) throws Exception {

        sceneManager = SceneManager.getInstance();

        mainStage = new Stage();
        loginStage = new Stage();
        logoutStage = new Stage();
        
        mainStage = primaryStage;

        // Load scenes for each stage using FXML
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(loginLoader.load());
        loginStage.setScene(loginScene);
        loginStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Scene mainScene = new Scene(mainLoader.load());
        mainStage.setScene(mainScene);
        mainStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader logoutLoader = new FXMLLoader(getClass().getResource("logout.fxml"));
        Scene logoutScene = new Scene(logoutLoader.load());
        logoutStage.setScene(logoutScene);
        logoutStage.initStyle(StageStyle.UNDECORATED);

        BaseController.initializeStages(loginStage, mainStage, logoutStage);
        sceneManager.preloadScenes();
        loginStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}