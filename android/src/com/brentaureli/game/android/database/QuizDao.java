package com.brentaureli.game.android.database;

import android.arch.persistence.room.Dao;

@Dao
public interface QuizDao {

    public void addQuiz(Quiz quiz);
}
