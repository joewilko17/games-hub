package com.gameshub;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import com.gameshub.view.BaseScene;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws IOException {
        
        mainStage = new Stage();

        BaseScene baseScene = new BaseScene(mainStage);

        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setScene(baseScene.getScene());
        mainStage.show();

    }

    public static void main(String[] args) {

        launch();

    }
    
}