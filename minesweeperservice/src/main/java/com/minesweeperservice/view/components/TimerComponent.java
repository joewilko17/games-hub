package com.minesweeperservice.view.components;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TimerComponent {

    private final Timeline timeline;
    private final IntegerProperty timeRemaining;
    private final Runnable timerTriggerEvent;
    

    public TimerComponent(int startTimeInSeconds, Runnable timerTriggerEvent) {
        this.timeRemaining = new SimpleIntegerProperty(startTimeInSeconds);
        this.timerTriggerEvent = timerTriggerEvent;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), _ -> {
            int currentTime = timeRemaining.get();
            if (currentTime > 0) {
                timeRemaining.set(currentTime - 1);
            } else {
                stop();
                if (this.timerTriggerEvent != null) {
                    this.timerTriggerEvent.run();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public IntegerProperty timeRemainingProperty() {
        return timeRemaining;
    }

    public void start() {
        timeline.playFromStart();
    }

    public void stop() {
        timeline.stop();
    }

    public void addTime(int timeAddedInSeconds) {
        timeRemaining.set(timeRemaining.get() + timeAddedInSeconds);
    }
    
    public void resetTimer(int resetTargetInSeconds) {
        stop();
        timeRemaining.set(resetTargetInSeconds);
    }
    
}
