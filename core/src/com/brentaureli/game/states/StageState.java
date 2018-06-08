package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.profiles.ProfileManager;

import java.util.Map;

public class StageState extends State {

    private TextureRegion stage1TextureRegion;
    private TextureRegionDrawable stage1TexRegionDrawable;
    private ImageButton stage1Button;

    private TextureRegion stage2TextureRegion;
    private TextureRegionDrawable stage2TexRegionDrawable;
    private ImageButton stage2Button;

    private TextureRegion stage3TextureRegion;
    private TextureRegionDrawable stage3TexRegionDrawable;
    private ImageButton stage3Button;

    private TextureRegion exitTextureRegion;
    private TextureRegionDrawable exitTexRegionDrawable;
    private ImageButton exitButton;

    private int gameHeight = Gdx.graphics.getHeight();
    private int gameWidth = Gdx.graphics.getWidth();
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font12 = generator.generateFont(parameter);
    private Texture background;
    private Texture stage1Btn;
    private Texture stage2Btn;
    private Texture stage3Btn;
    private Texture exitBtn;
    private Texture playerScore;

    private static final int ALL_BUTTONS_WIDTH = 300;
    private static final int ALL_BUTTONS_HEIGHT = 80;

    // dla funkcji draw()
    private static final int STAGE1_BUTTON_Y = 540;
    private static final int STAGE2_BUTTON_Y = 420;
    private static final int STAGE3_BUTTON_Y = 300;
    private static final int EXIT_BUTTON_Y = 10;
    private static final int PLAYER_SCORE_BUTTON_Y = 150;

    // POŁOŻENIE BUTTONÓW

    private int ALL_BUTTONS_X = QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2;

    //TODO: AWT working on Android? And why is this awt
//dla getX() / getY()
    private static final int stage1_y = QuizGame.HEIGHT - STAGE1_BUTTON_Y - ALL_BUTTONS_HEIGHT;
    private static final int stage2_y = QuizGame.HEIGHT - STAGE2_BUTTON_Y - ALL_BUTTONS_HEIGHT;
    private static final int stage3_y = QuizGame.HEIGHT - STAGE3_BUTTON_Y - ALL_BUTTONS_HEIGHT;
    private static final int exit_y = QuizGame.HEIGHT - EXIT_BUTTON_Y - ALL_BUTTONS_HEIGHT;


    private Rectangle stage1 = new Rectangle(ALL_BUTTONS_X, stage1_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
    private Rectangle stage2 = new Rectangle(ALL_BUTTONS_X, stage2_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
    private Rectangle stage3 = new Rectangle(ALL_BUTTONS_X, stage3_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
    private Rectangle exit = new Rectangle(ALL_BUTTONS_X, exit_y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
    private Stage stage;

    public StageState(GameStateManager gsm) {
        super(gsm);

        background = new Texture("stagesbg.png");
        stage1Btn = new Texture("stage1.png");
        stage2Btn = new Texture("stage2.png");
        stage3Btn = new Texture("stage3.png");
        exitBtn = new Texture("exitbutton.png");
        playerScore = new Texture("playerscore.png");

        stage1TextureRegion = new TextureRegion(stage1Btn);
        stage1TexRegionDrawable = new TextureRegionDrawable(stage1TextureRegion);
        stage1Button = new ImageButton(stage1TexRegionDrawable); //Set the button up

        stage2TextureRegion = new TextureRegion(stage2Btn);
        stage2TexRegionDrawable = new TextureRegionDrawable(stage2TextureRegion);
        stage2Button = new ImageButton(stage2TexRegionDrawable); //Set the button up

        stage3TextureRegion = new TextureRegion(stage3Btn);
        stage3TexRegionDrawable = new TextureRegionDrawable(stage3TextureRegion);
        stage3Button = new ImageButton(stage3TexRegionDrawable); //Set the button up

        exitTextureRegion = new TextureRegion(exitBtn);
        exitTexRegionDrawable = new TextureRegionDrawable(exitTextureRegion);
        exitButton = new ImageButton(exitTexRegionDrawable); //Set the button up


        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        Map<Integer, Integer> stageScoreMap = ProfileManager.getInstance().getCurrentProfile().getStageScoreMap();
        int score1 = stageScoreMap.get(1);
        int score2 = stageScoreMap.get(2);
        int score3 = stageScoreMap.get(3);
        int sum = score1 + score2 + score3;

        Label.LabelStyle nameLabelStyle = new Label.LabelStyle();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 120;
        BitmapFont font12 = generator.generateFont(parameter);
        nameLabelStyle.font = font12;

        Label label1 = new Label("SCORE: " + score1,nameLabelStyle);
        Label label2 = new Label("SCORE: " + score2,nameLabelStyle);
        Label label3 = new Label("SCORE: " + score3,nameLabelStyle);
        Label label4 = new Label("OVERALL PLAYER SCORE: ",nameLabelStyle);
        Label label5 = new Label("" + String.valueOf(sum),nameLabelStyle);


        Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.add(stage1Button);
        table.row();
        table.add(label1);
        table.row();
        table.add(stage2Button);
        table.row();
        table.add(label2);
        table.row();
        table.add(stage3Button);
        table.row();
        table.add(label3);
        table.row();
        table.add(label4);
        table.row();
        table.add(label5);
        table.row();
        table.add(exitButton);
        table.row();

        stage.addActor(table);

        stage1Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new PlayState(gsm, 1));
            }
        });
        stage2Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new PlayState(gsm, 2));
            }
        });
        stage3Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new PlayState(gsm, 3));
            }
        });
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new MenuState(gsm));
            }
        });
    }

    @Override
    public void handleInput() {
//        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
//            gsm.set(new MenuState(gsm));
//        }
//        if (Gdx.input.isTouched()) {
//            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            if (stage1.contains(tmp.x, tmp.y)) {
//                gsm.set(new PlayState(gsm, 1));
//            } else if (stage2.contains(tmp.x, tmp.y)) {
//                gsm.set(new PlayState(gsm, 2));
//            } else if (stage3.contains(tmp.x, tmp.y)) {
//                gsm.set(new PlayState(gsm, 3));
//            } else if (exit.contains(tmp.x, tmp.y)) {
//                gsm.set(new MenuState(gsm));
//        }
//
//        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
//        parameter.size = 25;
//        BitmapFont font12 = generator.generateFont(parameter);
//        Map<Integer, Integer> stageScoreMap = ProfileManager.getInstance().getCurrentProfile().getStageScoreMap();
//        int score1 = stageScoreMap.get(1);
//        int score2 = stageScoreMap.get(2);
//        int score3 = stageScoreMap.get(3);
//        int sum = score1 + score2 + score3;
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        sb.draw(stage1Btn, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2,  STAGE1_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
//        sb.draw(stage2Btn, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, STAGE2_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
//        sb.draw(stage3Btn, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, STAGE3_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
//        sb.draw(exitBtn, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, EXIT_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
//        sb.draw(playerScore, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2, PLAYER_SCORE_BUTTON_Y, ALL_BUTTONS_WIDTH, ALL_BUTTONS_HEIGHT);
//        font12.draw(sb, "SCORE: " + score1, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 5,STAGE1_BUTTON_Y-10);
//        font12.draw(sb, "SCORE: " + score2, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 5,STAGE2_BUTTON_Y-10);
//        font12.draw(sb, "SCORE: " + score3, QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 5,STAGE3_BUTTON_Y-10);
//        parameter.size = 45;
//        font12 = generator.generateFont(parameter);
//        font12.draw(sb, String.valueOf(sum), (gameWidth / 2)-5, 150);
        sb.end();
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        font12.dispose();
        background.dispose();
//        stage1bg.dispose();
//        stage2bg.dispose();
//        stage3bg.dispose();
        playerScore.dispose();

    }
}
