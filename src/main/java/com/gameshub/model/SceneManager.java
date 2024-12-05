package com.gameshub.model;

import java.util.HashMap;
import java.util.Map;

import com.gameshub.controller.BaseController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static SceneManager instance;

    private static final Map<String, Parent> scenes = new HashMap<>();
    private static final Map<String, BaseController> controllers = new HashMap<>();
    private Map<String, String> sceneFiles = new HashMap<>();

    public SceneManager() {
        sceneFiles.put("library", "/com/gameshub/library.fxml");
        sceneFiles.put("settings", "/com/gameshub/settings.fxml");
        sceneFiles.put("profile", "/com/gameshub/profile.fxml");
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void preloadScenes() {
        try {
            for (Map.Entry<String, String> entry : sceneFiles.entrySet()) {

                FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(entry.getValue()));
                Parent root = loader.load();
                BaseController controller = loader.getController();

                // Update scene with active profile data
                if (controller != null) {
                    controller.updateActiveProfileElements();
                }

                scenes.put(entry.getKey(), root);
                controllers.put(entry.getKey(), controller);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateScenes() {
        try {
            for (Map.Entry<String, String> entry : sceneFiles.entrySet()) {

                FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(entry.getValue()));
                BaseController controller = loader.getController();

                // Update scene with active profile data
                if (controller != null) {
                    controller.updateActiveProfileElements();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchScene(String sceneKey, Stage stage) {
        if (scenes.containsKey(sceneKey)) {
            Parent sceneRoot = scenes.get(sceneKey);
            Scene newScene = stage.getScene();

            newScene.setRoot(sceneRoot);
        } else {
            System.out.println("Scene with key '" + sceneKey + "' not found.");
        }
    }

    public Parent getScene(String sceneKey) {
        return scenes.get(sceneKey);
    }

    public BaseController getController(String sceneKey) {
        return controllers.get(sceneKey);
    }

    public String getSceneFXML(String sceneKey) {
        return sceneFiles.get(sceneKey);
    }
}
