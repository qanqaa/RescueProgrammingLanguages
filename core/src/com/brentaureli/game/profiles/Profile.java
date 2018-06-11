package com.brentaureli.game.profiles;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private String name;
    private String photo;
    private Map<Integer, Integer> stageScoreMap = new HashMap<>();
    private float timeForQuestion = 5;

    private void initializeStageScores() {
        stageScoreMap.put(1, 0);
        stageScoreMap.put(2, 0);
        stageScoreMap.put(3, 0);
    }

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
        initializeStageScores();
    }

    public Profile(String name, String photo) {
        this.name = name;
        this.photo = photo;
        initializeStageScores();
    }

    public Profile(String name, float timeForQuestion) {
        this.name = name;
        this.timeForQuestion = timeForQuestion;
        initializeStageScores();
    }

    public Profile(String name, String photo, float timeForQuestion) {
        this.name = name;
        this.photo = photo;
        this.timeForQuestion = timeForQuestion;
        initializeStageScores();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Map<Integer, Integer> getStageScoreMap() {
        return stageScoreMap;
    }

    public void setStageScoreMap(Map<Integer, Integer> stageScoreMap) {
        this.stageScoreMap = stageScoreMap;
    }

    public double getTimeForQuestion() {
        return timeForQuestion;
    }

    public void setTimeForQuestion(float timeForQuestion) {
        this.timeForQuestion = timeForQuestion;
    }
}
