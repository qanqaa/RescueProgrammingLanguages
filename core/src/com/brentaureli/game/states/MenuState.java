package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.profiles.ProfileManager;

import java.awt.*;

public class MenuState extends State{

    ProfileManager profileManager = ProfileManager.getInstance();
    // deklaracja wielkości buttonów
    private static final int START_BUTTON_WIDTH = 300;
    private static final int START_BUTTON_HEIGHT = 80;
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

    private Rectangle start = new Rectangle(start_x, start_y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
    private Rectangle profile = new Rectangle(profile_x, profile_y, PROFILE_BUTTON_WIDTH, PROFILE_BUTTON_HEIGHT);
    private Rectangle scores = new Rectangle(scores_x, scores_y, SCORES_BUTTON_WIDTH, SCORES_BUTTON_HEIGHT);
    private Rectangle exit = new Rectangle(exit_x, exit_y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture profileBtn;
    private Texture scoresBtn;

    public MenuState(GameStateManager gsm) {
        //TODO: LOAD PROFILE FROM MEMORY/CACHE/DATABASE?
        super(gsm);

        background = new Texture("bg.png");
        playBtn = new Texture(Gdx.files.internal("playbutton.png"));
        exitBtn = new Texture(Gdx.files.internal("exitbutton.png"));
        profileBtn = new Texture(Gdx.files.internal("profilebutton.png"));
        scoresBtn = new Texture(Gdx.files.internal("scoresbutton.png"));
    }

    @Override
    public void handleInput() {

        // można wykorzystać do zmiany koloru napisu po najechaniu na niego myszką HOVER
//        if(Gdx.input.getX() < start_x + START_BUTTON_WIDTH && Gdx.input.getX() > start_x && QuizGame.HEIGHT - Gdx.input.getY() < START_BUTTON_Y + START_BUTTON_HEIGHT && QuizGame.HEIGHT - Gdx.input.getY() > START_BUTTON_Y) {
//            // wczytaj nową teksturę
//        }

        if(Gdx.input.isTouched())
        {
            Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);

            if(start.contains(tmp.x,tmp.y))
            {
                gsm.set(new StageState(gsm));
            }
            else if(profile.contains(tmp.x,tmp.y))
            {
                gsm.set(new ProfileState(gsm));
            }
            else if(scores.contains(tmp.x,tmp.y))
            {
                gsm.set(new ScoresState(gsm));
            }
            else if(exit.contains(tmp.x,tmp.y))
            {
                // gdy starczy czasu, poprawić
                Gdx.app.exit();
            }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playBtn, QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2,  START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        sb.draw(profileBtn, QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2, PROFILE_BUTTON_Y, PROFILE_BUTTON_WIDTH, PROFILE_BUTTON_HEIGHT);
        sb.draw(scoresBtn, QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2, SCORES_BUTTON_Y, SCORES_BUTTON_WIDTH, SCORES_BUTTON_HEIGHT);
        sb.draw(exitBtn, QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        sb.end();
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
