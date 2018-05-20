package com.brentaureli.game.profiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Profile {

    private String name;
    private File photo;
    private Map<Integer, Integer> stageScoreMap = new HashMap<>();

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
    }

    public Profile(String name, File photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Map<Integer, Integer> getStageScoreMap() {
        return stageScoreMap;
    }

    public void setStageScoreMap(Map<Integer, Integer> stageScoreMap) {
        this.stageScoreMap = stageScoreMap;
    }
}
