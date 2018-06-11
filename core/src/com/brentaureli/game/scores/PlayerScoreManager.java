package com.brentaureli.game.scores;

import com.brentaureli.game.QuizGame;

import java.util.List;

/**
 * Created by Bonk on 2018-06-11.
 */

public class PlayerScoreManager {
    private static PlayerScoreManager instance = null;

    public static PlayerScoreManager getInstance() {
        if (instance == null) {
            instance = new PlayerScoreManager();
        }
        return instance;
    }

    public List<PlayerScore> getHighscores() {
        return QuizGame.getInstance().database.getHighscore();
    }

    public void addScore(PlayerScore score) {
        QuizGame.getInstance().database.insertScore(score);
    }
}
