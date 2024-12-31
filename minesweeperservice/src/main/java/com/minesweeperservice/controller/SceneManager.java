package com.minesweeperservice.controller;

import java.util.Map;

import com.minesweeperservice.view.BaseScene;
import com.minesweeperservice.view.FinishScene;
import com.minesweeperservice.view.GameScene;
import com.minesweeperservice.view.OptionsScene;
import com.minesweeperservice.view.SetupScene;
import com.minesweeperservice.view.SplashScene;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static SceneManager instance;

    private final Stage stage;
    private final Map<String, BaseScene> scenes;

    private int mainWidth = 800;
    private int mainHeight = 600;

    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            instance = new SceneManager(stage);
        }
        return instance;
    }

    private SceneManager(Stage stage) {
        this.stage = stage;
        this.scenes = new HashMap<>();
    }

    public Scene getScene(String sceneName) {

        BaseScene scene = scenes.get(sceneName);
        if (scene == null) {
            scene = createScene(sceneName);
        } else {
            scenes.put(sceneName, scene);
        }
        return scene.createScene(mainWidth, mainHeight);

    }

    private BaseScene createScene(String sceneName) {
        return switch (sceneName) {
            case "SplashScene" -> new SplashScene(stage);
            case "SetupScene" -> new SetupScene(stage);
            case "OptionsScene" -> new OptionsScene(stage);
            case "GameScene" -> new GameScene(stage);
            case "FinishScene" -> new FinishScene(stage);
            default -> null;
        };
    }

}
