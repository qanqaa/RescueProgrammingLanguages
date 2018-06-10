package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.commons.StageInfo;
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

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font12 = generator.generateFont(parameter);
    private Texture background;
    private Texture stage1Btn;
    private Texture stage2Btn;
    private Texture stage3Btn;
    private Texture exitBtn;
    private Texture playerScore;

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
                gsm.set(new PlayState(gsm, new StageInfo("JAVASCRIPT", 1)));
            }
        });
        stage2Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new PlayState(gsm, new StageInfo("C++", 2)));
            }
        });
        stage3Button.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new PlayState(gsm, new StageInfo("JAVA", 3)));
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
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        font12.dispose();
        background.dispose();
        stage1Btn.dispose();
        stage2Btn.dispose();
        stage1Btn.dispose();
        exitBtn.dispose();;
        playerScore.dispose();
        stage.dispose();

    }
}
