package com.brentaureli.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.database.AppDatabase;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new QuizGame(AppDatabase.getInstance(getApplicationContext())), config);
	}
}
