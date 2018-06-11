package com.brentaureli.game;

import com.brentaureli.game.questions.Question;

import java.util.List;

public interface DatabaseInterface {
    public List<Question> getQuestions();
}
