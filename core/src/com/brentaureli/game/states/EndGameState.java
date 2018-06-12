package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.QuizGame;

public class EndGameState extends State {

    private int gameHeight = QuizGame.HEIGHT;
    private int gameWidth = QuizGame.WIDTH;
    private int score;
    private boolean newRecord;
    private BitmapFont font = new BitmapFont();
    private String yourScoreText;
    private String newHighScoreText = "NEW HIGH SCORE!";
    private Texture background;
    private Texture exitBtn;
    private Texture playAgainBtn;


    private TextureRegion exitTextureRegion;
    private TextureRegionDrawable exitTexRegionDrawable;
    private ImageButton exitButton;
    private TextureRegion playAgainTextureRegion;
    private TextureRegionDrawable playAgainTexRegionDrawable;
    private ImageButton playAgainButton;

    private Stage stage;

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont font12;


    public EndGameState(GameStateManager gsm, int score, boolean newRecord) {
        super(gsm);
        background = new Texture("endgamebg.png");
        playAgainBtn = new Texture("playagainbutton.png");
        exitBtn = new Texture("exitbutton.png");
        this.score = score;
        this.newRecord = newRecord;
        this.yourScoreText = " YOUR SCORE: " + score;

        exitTextureRegion = new TextureRegion(exitBtn);
        exitTexRegionDrawable = new TextureRegionDrawable(exitTextureRegion);
        exitButton = new ImageButton(exitTexRegionDrawable); //Set the button up

        playAgainTextureRegion = new TextureRegion(playAgainBtn);
        playAgainTexRegionDrawable = new TextureRegionDrawable(playAgainTextureRegion);
        playAgainButton = new ImageButton(playAgainTexRegionDrawable); //Set the button up
        parameter.size = 100;
        font12 = generator.generateFont(parameter);

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui



        Table table = new Table();
        table.setFillParent(true);
        table.bottom();

        table.add(playAgainButton);
        table.row();
        table.add(exitButton);
        table.row();

        stage.addActor(table);

        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new MenuState(gsm));
            }
        });

        playAgainButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new StageState(gsm));
            }
        });
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
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, yourScoreText);
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font12.draw(sb, yourScoreText, gameWidth / 2 - glyphLayout.width / 2, gameHeight / 2 - 20);
        if (newRecord) {
            glyphLayout.setText(font, newHighScoreText);
            font12.draw(sb, newHighScoreText, gameWidth / 2 - glyphLayout.width / 2, gameHeight / 2 + 200);
        }
        sb.end();

        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        font.dispose();
        font12.dispose();
        background.dispose();
        playAgainBtn.dispose();
        exitBtn.dispose();
        stage.dispose();

    }
}
