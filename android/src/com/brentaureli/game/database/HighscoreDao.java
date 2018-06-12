package com.brentaureli.game.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface HighscoreDao {
    @Query("SELECT * FROM Highscore ORDER BY user_highscore DESC LIMIT 10" )
    List<Highscore> getAll();

    @Query("DELETE FROM Highscore")
    void nuke();

    @Insert
    void insert(Highscore highscore);
}
