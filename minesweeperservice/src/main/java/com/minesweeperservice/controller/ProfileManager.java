package com.minesweeperservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.minesweeperservice.model.Player;
import com.minesweeperservice.utils.JSONFileManager;

public class ProfileManager {

    private final static String FILE_PATH = "shared-data/profiles.json";
    private final Player CURRENT_PLAYER;
    private static ProfileManager instance;
    private JSONFileManager jsonFileManager;
    private List<Map<String, Object>> profileData;

    private ProfileManager(String currentProfileName) {
        jsonFileManager = new JSONFileManager(FILE_PATH);
        this.CURRENT_PLAYER = readPlayerData(currentProfileName);
    }

    public static ProfileManager getInstance(String currentProfileName) {
        if (instance == null) {
            instance = new ProfileManager(currentProfileName);
        }
        return instance;
    }

    public static ProfileManager getInstance() {
        if (instance == null) {
            try {
                throw new IllegalAccessException("ProfileManager not initialized with a player.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    // Method to get the current profiles data and transfer it into the
    // currentPlayer object
    @SuppressWarnings("unchecked")
    public Player readPlayerData(String currentProfileName) {
        System.out.println("Looking for profile match to " + currentProfileName);
        try {
            profileData = jsonFileManager.readJson();

            for (Map<String, Object> profile : profileData) {
                String username = (String) profile.get("username");

                if (currentProfileName.equals(username)) {
                    System.out.println("DEBUG: Username match: " + currentProfileName + " = " + username);

                    // Access preferences
                    Map<String, Object> preferences = (Map<String, Object>) profile.getOrDefault("preferences",
                            new HashMap<>());
                    String defaultDifficulty = (String) preferences.getOrDefault("defaultDifficulty", "easy");
                    boolean showHints = (boolean) preferences.getOrDefault("showHints", true);

                    // Access gamesData -> minesweeper -> highScores and playerStats
                    Map<String, Object> gamesData = (Map<String, Object>) profile.getOrDefault("gamesData",
                            new HashMap<>());
                    Map<String, Object> minesweeperData = (Map<String, Object>) gamesData.getOrDefault("minesweeper",
                            new HashMap<>());
                    Map<String, Integer> highScores = (Map<String, Integer>) minesweeperData
                            .getOrDefault("highScores", new HashMap<>());
                    Map<String, Integer> playerStats = (Map<String, Integer>) minesweeperData
                            .getOrDefault("playerStats", new HashMap<>());

                    // Create Player object
                    Player player = new Player(username, defaultDifficulty, showHints, highScores, playerStats);

                    // Debugging
                    System.out.println("Loaded Player: " + player);
                    System.out.println("High Scores: " + highScores);
                    System.out.println("Player Stats: " + playerStats);

                    return player;
                }
            }

            System.out.println("No matching data found for the username: " + currentProfileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Player getPlayer() {
        return CURRENT_PLAYER;
    }

    // Method to save & update the changes made to current profile object into the
    // profiles.json File
    @SuppressWarnings("unchecked")
    public void updatePlayerData() {
        try {
            for (Map<String, Object> profile : profileData) {
                String username = (String) profile.get("username");
                if (username.equals(CURRENT_PLAYER.getPlayerName())) {

                    Map<String, Object> gamesData = (Map<String, Object>) profile.get("gamesData");
                    if (gamesData == null) {
                        gamesData = new HashMap<>();
                        profile.put("gamesData", gamesData);
                    }

                    if (!gamesData.containsKey("minesweeper")) {
                        // Initialize default data for Minesweeper
                        Map<String, Object> defaultMinesweeperData = new HashMap<>();
                        defaultMinesweeperData.put("highScores", new HashMap<Integer, Integer>());
                        defaultMinesweeperData.put("playerStats", new HashMap<String, Object>());
                        gamesData.put("minesweeper", defaultMinesweeperData);
                    }

                    Map<String, Object> minesweeperData = (Map<String, Object>) gamesData.get("minesweeper");
                    minesweeperData.put("highScores", CURRENT_PLAYER.getHighScores());
                    CURRENT_PLAYER.sortHighScores();
                    minesweeperData.put("playerStats", CURRENT_PLAYER.getPlayerStats());

                    jsonFileManager.writeJson(profileData);
                    System.out.println("Profile data for " + username + " updated successfully");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
