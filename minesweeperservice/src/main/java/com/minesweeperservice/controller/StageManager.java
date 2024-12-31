package com.minesweeperservice.controller;

import javafx.stage.Stage;

public class StageManager {

    private static StageManager instance;

    private Stage mainStage;

    private StageManager() {

    }

    public static StageManager getInstance() {
        if(instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }
}
