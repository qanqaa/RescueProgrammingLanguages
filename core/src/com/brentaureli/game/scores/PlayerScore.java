package com.brentaureli.game.scores;


import com.brentaureli.game.profiles.Profile;

public class PlayerScore {

    private Profile profile;
    private int score;

    public PlayerScore() {
    }

    public PlayerScore(Profile profile, int score) {
        this.profile = profile;
        this.score = score;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
