package com.brentaureli.game.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM Question")
    List<Question> getAll();

    @Insert
    void insertAll(Question... questions);
}
