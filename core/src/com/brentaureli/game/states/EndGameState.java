package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class EndGameState extends State {

    private int gameHeight = Gdx.graphics.getHeight();
    private int gameWidth = Gdx.graphics.getWidth();
    private int stageTableStartingWidth = gameWidth / 6;
    private int stageTableHeight = gameHeight - 100;
    private int score;
    private boolean newRecord;
    private BitmapFont font = new BitmapFont();
    private String endGameText = "END GAME";
    private String yourScoreText;
    private String newHighScoreText = "NEW HIGH SCORE!";

    public EndGameState(GameStateManager gsm, int score, boolean newRecord) {
        super(gsm);
        this.score = score;
        this.newRecord = newRecord;
        this.yourScoreText = " YOUR SCORE: " + score;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.set(new StageState(gsm));
        }
        if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.getData().setScale(2, 2);
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, endGameText);
        font.draw(sb, endGameText, gameWidth / 2 - glyphLayout.width / 2, gameHeight / 2 + 20);
        glyphLayout.setText(font, yourScoreText);
        font.draw(sb, yourScoreText, gameWidth / 2 - glyphLayout.width / 2, gameHeight / 2 - 20);
        if (newRecord) {
            glyphLayout.setText(font, newHighScoreText);
            font.draw(sb, newHighScoreText, gameWidth / 2 - glyphLayout.width / 2, gameHeight / 2 - 60);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();

    }
}
