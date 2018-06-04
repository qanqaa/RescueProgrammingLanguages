package com.brentaureli.game.android.database;

import android.arch.persistence.room.Dao;

@Dao
public interface UserDao {

    public void addUser(User user);

}
