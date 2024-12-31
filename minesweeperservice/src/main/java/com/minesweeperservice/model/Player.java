package com.minesweeperservice.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Player {

    private String playerName;
    private String defaultDifficulty;
    private boolean showHints;
    private Map<String, Integer> highScores;
    private Map<String, Integer> playerStats;

    public Player(String playerName, String defaultDifficulty, boolean showHints, Map<String, Integer> highScores,
            Map<String, Integer> playerStats) {
        this.playerName = playerName;
        this.defaultDifficulty = defaultDifficulty;
        this.showHints = showHints;
        this.highScores = highScores;
        this.playerStats = playerStats;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDefaultDifficulty() {
        return defaultDifficulty;
    }

    public void setDefaultDifficulty(String defaultDifficulty) {
        this.defaultDifficulty = defaultDifficulty;
    }

    public boolean isShowHints() {
        return showHints;
    }

    public void setShowHints(boolean showHints) {
        this.showHints = showHints;
    }

    public Map<String, Integer> getHighScores() {
        return highScores;
    }

    public void setHighScores(Map<String, Integer> highScores) {
        this.highScores = highScores;
    }

    public Map<String, Integer> getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(Map<String, Integer> playerStats) {
        this.playerStats = playerStats;
    }

    public void addHighScore(String position, int score) {
        highScores.put(position, score);
    }

    public void sortHighScores() {
        // Create a new map to store sorted high scores by values
        Map<Integer, Integer> sortedHighScores = new LinkedHashMap<>();
        // Convert the map to Integer keys and values if they are stored as Strings
        for (Map.Entry<String, Integer> entry : getHighScores().entrySet()) {
            try {
                Integer position = Integer.parseInt(entry.getKey());
                Integer score = entry.getValue();
                sortedHighScores.put(position, score);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing high score data: " + entry);
                e.printStackTrace();
            }
        }

        // Sort the map by value (score) in descending order
        sortedHighScores = sortedHighScores.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) 
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, _) -> e1, LinkedHashMap::new));

        // Reassign the keys to be in the order 1, 2, 3, 4 (i.e., the highest score gets
        // 1)
        Map<String, Integer> orderedHighScores = new LinkedHashMap<>();
        int rank = 1;
        for (Map.Entry<Integer, Integer> entry : sortedHighScores.entrySet()) {
            orderedHighScores.put(String.valueOf(rank), entry.getValue());
            rank++;
        }

        // Set the ordered high scores back to the original map
        setHighScores(orderedHighScores);

        // Debugging output
        System.out.println("Ordered High Scores: " + orderedHighScores);
    }



    public void updatePlayerStat(String statName, int value) {
        playerStats.put(statName, value);
    }

    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", defaultDifficulty=" + defaultDifficulty + ", showHints="
                + showHints + ", highScores=" + highScores + ", playerStats=" + playerStats + "]";
    }

}
