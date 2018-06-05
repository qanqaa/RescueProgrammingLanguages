package com.brentaureli.game.android.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Bonk on 2018-06-03.
 */
@Database(entities = {User.class, Quiz.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract QuizDao quizDao();
}
