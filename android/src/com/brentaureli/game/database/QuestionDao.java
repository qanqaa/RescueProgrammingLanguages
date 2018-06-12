package com.brentaureli.game.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM Question WHERE category = :category")
    List<Question> getAll(int category);

    @Query("DELETE FROM Question")
    void nuke();

    @Insert
    void insertAll(Question... questions);
}
