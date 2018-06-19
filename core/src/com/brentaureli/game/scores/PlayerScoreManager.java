package com.brentaureli.game.scores;

import com.brentaureli.game.QuizGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<PlayerScore> getRandom10HighScores() {
        Random random = new Random();
        List<PlayerScore> playerScores = QuizGame.getInstance().database.getHighscore();
        List<PlayerScore> randomScores = new ArrayList<>();
        for (int i = 0; i < 10 && playerScores.size() > 0; i++) {
            int randIndex = random.nextInt(playerScores.size());
            randomScores.add(playerScores.get(randIndex));
            playerScores.remove(randIndex);
        }
        randomScores.sort((p1, p2) -> p2.getScore() - p1.getScore());
        return randomScores;
    }

    public void addScore(PlayerScore score) {
        QuizGame.getInstance().database.insertScore(score);
    }
}
