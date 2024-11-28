package com.gameshub;

import com.gameshub.controller.BaseController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage loginStage = new Stage();
        Stage logoutStage = new Stage();
        Stage mainStage = new Stage();

        // Load scenes for each stage using FXML
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(loginLoader.load());
        loginStage.setScene(loginScene);
        loginStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("library.fxml"));
        Scene mainScene = new Scene(mainLoader.load());
        mainStage.setScene(mainScene);
        mainStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader logoutLoader = new FXMLLoader(getClass().getResource("logout.fxml"));
        Scene logoutScene = new Scene(logoutLoader.load());
        logoutStage.setScene(logoutScene);
        logoutStage.initStyle(StageStyle.UNDECORATED);

        BaseController.initializeStages(loginStage, mainStage, logoutStage);

        loginStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}