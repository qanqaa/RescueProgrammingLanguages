package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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


    public EndGameState(GameStateManager gsm, int score, boolean newRecord) {
        super(gsm);
        this.score = score;
        this.newRecord = newRecord;
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
        font.draw(sb, "END GAME", gameWidth / 2 - 30, gameHeight / 2);
        font.draw(sb, "YOUR SCORE: " + score, gameWidth / 2 - 30, gameHeight / 2 - 40);
        if (newRecord) {
            font.draw(sb, "NEW HIGHSCORE!", gameWidth / 2 - 30, gameHeight / 2 - 80);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();

    }
}
