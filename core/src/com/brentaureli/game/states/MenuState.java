package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.profiles.ProfileManager;


public class MenuState extends State {


    private Stage stage;

    //for menu buttons
    private TextureRegion playTextureRegion;
    private TextureRegionDrawable playTexRegionDrawable;
    private ImageButton playButton;

    private TextureRegion profileTextureRegion;
    private TextureRegionDrawable profileTexRegionDrawable;
    private ImageButton profileButton;

    private TextureRegion scoresTextureRegion;
    private TextureRegionDrawable scoresTexRegionDrawable;
    private ImageButton scoresButton;

    private TextureRegion exitTextureRegion;
    private TextureRegionDrawable exitTexRegionDrawable;
    private ImageButton exitButton;


    ProfileManager profileManager = ProfileManager.getInstance();

    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture profileBtn;
    private Texture scoresBtn;
    private float xRot; // here


    public MenuState(GameStateManager gsm) {
        super(gsm);


        background = new Texture("bg.png");
        playBtn = new Texture(Gdx.files.internal("playbutton.png"));
        exitBtn = new Texture(Gdx.files.internal("exitbutton.png"));
        profileBtn = new Texture(Gdx.files.internal("profilebutton.png"));
        scoresBtn = new Texture(Gdx.files.internal("scoresbutton.png"));

        playTextureRegion = new TextureRegion(playBtn);
        playTexRegionDrawable = new TextureRegionDrawable(playTextureRegion);
        playButton = new ImageButton(playTexRegionDrawable); //Set the button up

        profileTextureRegion = new TextureRegion(profileBtn);
        profileTexRegionDrawable = new TextureRegionDrawable(profileTextureRegion);
        profileButton = new ImageButton(profileTexRegionDrawable); //Set the button up

        scoresTextureRegion = new TextureRegion(scoresBtn);
        scoresTexRegionDrawable = new TextureRegionDrawable(scoresTextureRegion);
        scoresButton = new ImageButton(scoresTexRegionDrawable); //Set the button up

        exitTextureRegion = new TextureRegion(exitBtn);
        exitTexRegionDrawable = new TextureRegionDrawable(exitTextureRegion);
        exitButton = new ImageButton(exitTexRegionDrawable); //Set the button up

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.add(playButton);
        table.row();
        table.add(profileButton);
        table.row();
        table.add(scoresButton);
        table.row();
        table.add(exitButton);
        table.row();

        stage.addActor(table);

        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new StageState(gsm));
            }
        });
        profileButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new ProfileState(gsm));
            }
        });
        scoresButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new ScoresState(gsm));
            }
        });
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                Gdx.app.exit();
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
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        stage.draw();
        stage.act();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        exitBtn.dispose();
        profileBtn.dispose();
        scoresBtn.dispose();
        stage.dispose();


    }
}
