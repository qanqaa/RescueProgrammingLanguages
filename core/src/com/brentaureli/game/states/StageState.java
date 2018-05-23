package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.profiles.ProfileManager;

import java.awt.*;
import java.util.Map;

public class StageState extends State {

    private int gameHeight = Gdx.graphics.getHeight();
    private int gameWidth = Gdx.graphics.getWidth();
    BitmapFont font = new BitmapFont();

    private Texture background;
    private Texture stage1bg;
    private Texture stage2bg;
    private Texture stage3bg;
    private Texture playerScore;

    private static final int ALL_BUTTONS_WIDTH = 300;
    private static final int ALL_BUTTONS_HEIGHT = 80;

    // dla funkcji draw()
    private static final int STAGE1_BUTTON_Y = 540;
    private static final int STAGE2_BUTTON_Y = 420;
    private static final int STAGE3_BUTTON_Y = 300;
    private static final int PLAYER_SCORE_BUTTON_Y = 150;

    // POŁOŻENIE BUTTONÓW

    private int ALL_BUTTONS_X = QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2;

    //TODO: AWT working on Android? And why is this awt
//dla getX() / getY()
    private static final int stage1_y = QuizGame.HEIGHT - STAGE1_BUTTON_Y - ALL_BUTTONS_HEIGHT;
    private static final int stage2_y = QuizGame.HEIGHT - STAGE2_BUTTON_Y - ALL_BUTTONS_HEIGHT;
    private static final int stage3_y = QuizGame.HEIGHT - STAGE3_BUTTON_Y - ALL_BUTTONS_HEIGHT;


    private Rectangle stage1 = new Rectangle(ALL_BUTTONS_X, stage1_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
    private Rectangle stage2 = new Rectangle(ALL_BUTTONS_X, stage2_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
    private Rectangle stage3 = new Rectangle(ALL_BUTTONS_X, stage3_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);


    public StageState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("stagesbg.png");
        stage1bg = new Texture("stage1.png");
        stage2bg = new Texture("stage2.png");
        stage3bg = new Texture("stage3.png");
        playerScore = new Texture("playerscore.png");
    }

    @Override
    public void handleInput() {

        // można wykorzystać do zmiany koloru napisu po najechaniu na niego myszką HOVER
//        if(Gdx.input.getX() < start_x + START_BUTTON_WIDTH && Gdx.input.getX() > start_x && QuizGame.HEIGHT - Gdx.input.getY() < START_BUTTON_Y + START_BUTTON_HEIGHT && QuizGame.HEIGHT - Gdx.input.getY() > START_BUTTON_Y) {
//            // wczytaj nową teksturę
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }
        if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            // texture x is the x position of the texture
//            // texture y is the y position of the texture
//            // texturewidth is the width of the texture (you can get it with texture.getWidth() or textureRegion.getRegionWidth() if you have a texture region
//            // textureheight is the height of the texture (you can get it with texture.getHeight() or textureRegion.getRegionhHeight() if you have a texture region
            if (stage1.contains(tmp.x, tmp.y)) {
                gsm.set(new PlayState(gsm, 1));
            } else if (stage2.contains(tmp.x, tmp.y)) {
                gsm.set(new PlayState(gsm, 2));
            } else if (stage3.contains(tmp.x, tmp.y)) {
                gsm.set(new PlayState(gsm, 3));
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        Map<Integer, Integer> stageScoreMap = ProfileManager.getInstance().getCurrentProfile().getStageScoreMap();
        int score1 = stageScoreMap.get(1);
        int score2 = stageScoreMap.get(2);
        int score3 = stageScoreMap.get(3);
        int sum = score1 + score2 + score3;
        sb.begin();
        font.getData().setScale(1, 1);
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(stage1bg, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2,  STAGE1_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
        sb.draw(stage2bg, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, STAGE2_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
        sb.draw(stage3bg, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, STAGE3_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
        sb.draw(playerScore, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, PLAYER_SCORE_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
        font.draw(sb, "SCORE: " + score1, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 5,STAGE1_BUTTON_Y-10);
        font.draw(sb, "SCORE: " + score2, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 5,STAGE2_BUTTON_Y-10);
        font.draw(sb, "SCORE: " + score3, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 5,STAGE3_BUTTON_Y-10);
        font.draw(sb, String.valueOf(sum), gameWidth / 2, 100);
        sb.end();

    }

    @Override
    public void dispose() {
        font.dispose();

    }
}
