package com.brentaureli.game.commons;

public class StageInfo {

    String stageName;
    int level;


    public StageInfo(String stageName, int level) {
        this.stageName = stageName;
        this.level = level;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
