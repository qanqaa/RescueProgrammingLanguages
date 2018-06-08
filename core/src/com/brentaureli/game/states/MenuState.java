package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.profiles.ProfileManager;


public class MenuState extends State {

    private boolean peripheralAvailable;
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
    // deklaracja wielkości buttonów
    private static final int START_BUTTON_WIDTH = 600;
    private static final int START_BUTTON_HEIGHT = 160;
    private static final int PROFILE_BUTTON_WIDTH = 300;
    private static final int PROFILE_BUTTON_HEIGHT = 80;
    private static final int SCORES_BUTTON_WIDTH = 300;
    private static final int SCORES_BUTTON_HEIGHT = 80;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 80;

    // dla funkcji draw()
    private static final int START_BUTTON_Y = 380;
    private static final int PROFILE_BUTTON_Y = 280;
    private static final int SCORES_BUTTON_Y = 180;
    private static final int EXIT_BUTTON_Y = 80;

    // POŁOŻENIE BUTTONÓW

    private int start_x = QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2;
    private int profile_x = QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2;
    private int scores_x = QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2;
    private int exit_x = QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;

    //dla getX() / getY()
    private static final int start_y = QuizGame.HEIGHT - START_BUTTON_Y - START_BUTTON_HEIGHT;
    private static final int profile_y = QuizGame.HEIGHT - PROFILE_BUTTON_Y - PROFILE_BUTTON_HEIGHT;
    private static final int scores_y = QuizGame.HEIGHT - SCORES_BUTTON_Y - SCORES_BUTTON_HEIGHT;
    private static final int exit_y = QuizGame.HEIGHT - EXIT_BUTTON_Y - EXIT_BUTTON_HEIGHT;


    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture profileBtn;
    private Texture scoresBtn;
    private float xRot; // here



    public MenuState(GameStateManager gsm) {
        //TODO: LOAD PROFILE FROM MEMORY/CACHE/DATABASE?
        super(gsm);

        peripheralAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);


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
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new StageState(gsm));
            }
        });
        profileButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new ProfileState(gsm));
            }
        });
        scoresButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new ScoresState(gsm));
            }
        });
        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                Gdx.app.exit();
            }
        });


    }


    @Override
    public void handleInput() {

        // można wykorzystać do zmiany koloru napisu po najechaniu na niego myszką HOVER
//        if(Gdx.input.getX() < start_x + START_BUTTON_WIDTH && Gdx.input.getX() > start_x && QuizGame.HEIGHT - Gdx.input.getY() < START_BUTTON_Y + START_BUTTON_HEIGHT && QuizGame.HEIGHT - Gdx.input.getY() > START_BUTTON_Y) {
//            // wczytaj nową teksturę
//        }



//        if(Gdx.input.isTouched())
//        {
//            Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
////            Gdx.app.log("xy", tmp.x + " " + tmp.y);
//            if(start.contains(tmp.x,tmp.y))
//            {
//                gsm.set(new StageState(gsm));
//            }
//            else if(profile.contains(tmp.x,tmp.y))
//            {
//                gsm.set(new ProfileState(gsm));
//            }
//            else if(scores.contains(tmp.x,tmp.y))
//            {
//                gsm.set(new ScoresState(gsm));
//            }
//            else if(exit.contains(tmp.x,tmp.y))
//            {
//                // gdy starczy czasu, poprawić
//                Gdx.app.exit();
//            }
//
//        }
    }

    @Override
    public void update(float dt) {handleInput();
        if(peripheralAvailable) {
            xRot = Gdx.input.getAccelerometerX();
            Gdx.app.log("ACCELEROMETER", "NANANAN:  " + xRot);
        }
    }

    @Override
    public void render(SpriteBatch sb) {



        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        sb.draw(playBtn, QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2,  START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
//        sb.draw(profileBtn, QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2, PROFILE_BUTTON_Y, PROFILE_BUTTON_WIDTH, PROFILE_BUTTON_HEIGHT);
//        sb.draw(scoresBtn, QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2, SCORES_BUTTON_Y, SCORES_BUTTON_WIDTH, SCORES_BUTTON_HEIGHT);
//        sb.draw(exitBtn, QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
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

    }
}
