package com.gameshubservice.model;

import java.io.IOException;

public class GameLauncher {
    public static void launchMinesweeperService(String currentProfileName) {
        // Construct the command with JavaFX path, modules, and the currentProfileName
        // argument
        String javafxPath = "C:\\javafx-sdk-23.0.1\\lib";
        String jarPath = "minesweeperservice/target/minesweeperservice-1.0.jar";

        // Command to execute the .jar with JavaFX modules and argument
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                "--module-path", javafxPath,
                "--add-modules", "javafx.controls,javafx.fxml",
                "-jar", jarPath,
                currentProfileName); // Pass the currentProfileName as an argument to the jar

        try {
            // Start the process
            System.out.println("Launching with command: " + String.join(" ", processBuilder.command()));
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.getInputStream().transferTo(System.out);
            process.getErrorStream().transferTo(System.err);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
