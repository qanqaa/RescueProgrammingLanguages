package com.rsp.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brentaureli.game.QuizGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = QuizGame.WIDTH;
        config.height = QuizGame.HEIGHT;
        config.title = QuizGame.TITLE;
		new LwjglApplication(new QuizGame(), config);
	}
}
