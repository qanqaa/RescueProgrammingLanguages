package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.game.scores.PlayerScore;
import com.brentaureli.game.scores.PlayerScoreManagerMock;

import java.util.List;

public class ProfileState extends State {


    private List<PlayerScore> playerScoreList;
    private BitmapFont font;

    public ProfileState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        //TODO: IMPLEMENT DATABASE HIGHSCORES
        //playerScoreList = PlayerScoreManager.getTop10HighScores();
        playerScoreList = PlayerScoreManagerMock.list20PlayerScores();
        if (playerScoreList.size() > 10) {
            playerScoreList.subList(10, playerScoreList.size()).clear();
        }

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    //TODO: add FreeTypeFontGenerator to get scalable bitmap fonts

    @Override
    public void render(SpriteBatch sb) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "HIGH SCORES");
        sb.begin();

        int nameWidth = width / 6;
        int scoreWidth = 2 * width / 3;
        //TODO: ADD PROFILE PHOTOS?
        // int photoWidth = width/7;

        font.getData().setScale(2, 2);
        font.draw(sb, "HIGH SCORES", (width / 2) - (layout.width / 2), height - 50);
        font.draw(sb, "NAME", nameWidth, height - 150);
        font.draw(sb, "SCORE", scoreWidth, height - 150);

        height -= 200;
        for (PlayerScore playerScore : playerScoreList) {
            height -= 40;
            font.draw(sb, playerScore.getProfile().getName(), nameWidth, height);
            font.draw(sb, String.valueOf(playerScore.getScore()), scoreWidth, height);
            //TODO: RENDER PFOILE PHOTOS FOR EACH SCORE?
        }
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
