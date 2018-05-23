package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.game.scores.PlayerScore;
import com.brentaureli.game.scores.PlayerScoreManagerMock;

import java.util.List;

public class ScoresState extends State {


    private List<PlayerScore> playerScoreList;
    private BitmapFont font;
    private Texture background;

    public ScoresState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        background = new Texture(Gdx.files.internal("highscoresbg.png"));
        //TODO: IMPLEMENT DATABASE HIGHSCORES
        //playerScoreList = PlayerScoreManager.getTop10HighScores();
        playerScoreList = PlayerScoreManagerMock.getInstance().prepare20Players();
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
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        int nameWidth = width / 6;
        int scoreWidth = 2 * width / 3;
        //TODO: profile photos?
        // int photoWidth = width/7;

        font.getData().setScale(2, 2);

        height -= 200;
        for (PlayerScore playerScore : playerScoreList) {
            height -= 40;
            font.draw(sb, playerScore.getProfile().getName(), nameWidth, height);
            font.draw(sb, String.valueOf(playerScore.getScore()), scoreWidth, height);
            //TODO: render photos for each score?
        }
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
