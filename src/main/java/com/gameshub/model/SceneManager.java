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
    private Map<String, BaseController> cachedControllers = new HashMap<>();

    public SceneManager() {
        sceneFiles.put("library", "/com/gameshub/library.fxml");
        sceneFiles.put("settings", "/com/gameshub/settings.fxml");
        sceneFiles.put("profile", "/com/gameshub/profile.fxml");
        sceneFiles.put("login", "/com/gameshub/login.fxml");
        sceneFiles.put("logout", "/com/gameshub/logout.fxml");
        sceneFiles.put("createprofile", "/com/gameshub/createprofile.fxml");
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void addScene(String sceneName, Parent root, BaseController controller) {
        scenes.put(sceneName, root);
        controllers.put(sceneName, controller);
        System.out.println("Scene added: " + sceneName);
    }

    public void preloadScenes() {
        try {
            for (Map.Entry<String, String> entry : sceneFiles.entrySet()) {

                FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(entry.getValue()));
                Parent root = loader.load();
                BaseController controller = loader.getController();
                scenes.put(entry.getKey(), root);
                controllers.put(entry.getKey(), controller);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateScenes() {
        try {
            System.out.println("updateScenes has been called");

            for (Map.Entry<String, String> entry : sceneFiles.entrySet()) {
                String sceneName = entry.getKey();
                String fxmlPath = entry.getValue();
                BaseController controller = cachedControllers.get(sceneName);

                if (controller == null) {
                    // Load the FXML and cache the controller if it's not already cached
                    FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
                    loader.load(); // Ensure FXML is loaded
                    controller = loader.getController();
                    System.out.println("This is the current controller: " + controller);
                    if (controller != null) {
                        cachedControllers.put(sceneName, controller);
                    }
                }

                // Call updateActiveProfileElements if controller is valid
                if (controller != null) {
                    System.out.println("Calling updateActiveProfileElements for: " + sceneName);
                    controller.updateActiveProfileElements();
                } else {
                    System.out.println("Controller for scene '" + sceneName + "' is null.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateScene(Stage stage) {
        try {
            // Get the current scene's root from mainStage
            Scene currentScene = stage.getScene();
            System.out.println("current scene is: " + currentScene);
            if (currentScene != null) {
                Parent currentRoot = currentScene.getRoot();
                System.out.println("current root is: " + currentScene);
                // Find the controller associated with this root
                for (Map.Entry<String, Parent> entry : scenes.entrySet()) {
                    System.out.println("key: " + entry.getKey());
                    System.out.println("value: " + entry.getValue());
                    
                    if (entry.getValue() == currentRoot) {
                        BaseController controller = controllers.get(entry.getKey());
                        if (controller != null) {
                            controller.updateActiveProfileElements(); // Refresh UI
                            System.out.println("Scene updated for: " + entry.getKey());
                        }
                        return;
                    }
                }
            }
            System.out.println("No matching scene found to update.");
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
