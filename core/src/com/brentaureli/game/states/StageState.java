package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.brentaureli.game.profiles.ProfileManager;

import java.awt.*;
import java.util.Map;

public class StageState extends State {

    private int gameHeight = Gdx.graphics.getHeight();
    private int gameWidth = Gdx.graphics.getWidth();
    private int stageTableStartingWidth = gameWidth / 6;
    private int stageTableHeight = gameHeight - 100;
    BitmapFont font = new BitmapFont();
    //    // deklaracja wielkości buttonów
    public static final int STAGE_WIDTH = 200;
    public static final int STAGE_HEIGHT = 80;

    //    // dla funkcji draw()
    public static final int START_BUTTON_Y = 450;
    public static final int PROFILE_BUTTON_Y = 350;
    public static final int SCORES_BUTTON_Y = 250;
    public static final int EXIT_BUTTON_Y = 150;
    //
//    // POŁOŻENIE BUTTONÓW
//
    private int stage1PositionX = stageTableStartingWidth;
    private int stage2PositionX = stage1PositionX + STAGE_WIDTH + 50;
    private int stage3PositionX = stage2PositionX + STAGE_WIDTH + 50;
    //
//    //dla getX() / getY()
    //TODO: AWT working on Android? And why is this awt
    private Rectangle stage1 = new Rectangle(stage1PositionX, gameHeight - stageTableHeight, STAGE_WIDTH, STAGE_HEIGHT);
    private Rectangle stage2 = new Rectangle(stage2PositionX, gameHeight - stageTableHeight, STAGE_WIDTH, STAGE_HEIGHT);
    private Rectangle stage3 = new Rectangle(stage3PositionX, gameHeight - stageTableHeight, STAGE_WIDTH, STAGE_HEIGHT);

    public StageState(GameStateManager gsm) {
        super(gsm);
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
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(stage1PositionX, stageTableHeight - STAGE_HEIGHT, STAGE_WIDTH, STAGE_HEIGHT);
        shapeRenderer.rect(stage2PositionX, stageTableHeight - STAGE_HEIGHT, STAGE_WIDTH, STAGE_HEIGHT);
        shapeRenderer.rect(stage3PositionX, stageTableHeight - STAGE_HEIGHT, STAGE_WIDTH, STAGE_HEIGHT);
        shapeRenderer.end();
        sb.begin();
        font.getData().setScale(2, 2);
        font.draw(sb, "STAGES", gameWidth / 2, gameHeight - 50);
        font.draw(sb, "STAGE 1", stage1PositionX + (STAGE_WIDTH / 2) - 60, stageTableHeight - (STAGE_HEIGHT / 2) + 10);
        font.draw(sb, "SCORE: " + score1, stage1PositionX, stageTableHeight - STAGE_HEIGHT);
        font.draw(sb, "STAGE 2", stage2PositionX + (STAGE_WIDTH / 2) - 60, stageTableHeight - (STAGE_HEIGHT / 2) + 10);
        font.draw(sb, "SCORE: " + score2, stage2PositionX, stageTableHeight - STAGE_HEIGHT);
        font.draw(sb, "STAGE 3", stage3PositionX + (STAGE_WIDTH / 2) - 60, stageTableHeight - (STAGE_HEIGHT / 2) + 10);
        font.draw(sb, "SCORE: " + score3, stage3PositionX, stageTableHeight - STAGE_HEIGHT);
        font.draw(sb, "OVERALL PLAYER SCORE", gameWidth / 2 - 200, 200);
        font.draw(sb, String.valueOf(sum), gameWidth / 2, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();

    }
}
