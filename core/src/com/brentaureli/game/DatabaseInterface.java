package com.brentaureli.game;

import com.brentaureli.game.questions.Question;
import com.brentaureli.game.scores.PlayerScore;

import java.util.List;

public interface DatabaseInterface {
    public List<Question> getQuestions(int category);
    public List<PlayerScore> getHighscore();
    public void insertScore(PlayerScore score);
}
