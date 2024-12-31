package com.minesweeperservice.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class ScoreComponent extends Label {

    private Label scoreLabel;

    private int score;

    public ScoreComponent(int initialScore) {
        initialiseComponent(initialScore);
    }

    public void initialiseComponent(int initialScore) {
        score += initialScore;
        scoreLabel = new Label("Score: " + score);
        scoreLabel.getStyleClass().add("game-score-component");
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setPrefSize(130, 40);
    }

    public Label getScoreComponent() {
        return scoreLabel;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int scoreToAdd) {
        score += scoreToAdd;
        scoreLabel.setText("Score: " + score);
    }

    public void setScore(int scoreToSet) {
        score = scoreToSet;
        scoreLabel.setText("Score: " + score);
    }

    public void resetScore() {
        score = 0;
        scoreLabel.setText("Score: " + score);
    }

}
