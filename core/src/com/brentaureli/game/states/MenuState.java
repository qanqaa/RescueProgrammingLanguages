package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.game.QuizGame;

public class MenuState extends State{

    public static final int START_BUTTON_WIDTH = 150;
    public static final int START_BUTTON_HEIGHT = 80;
    public static final int PROFILE_BUTTON_WIDTH = 150;
    public static final int PROFILE_BUTTON_HEIGHT = 80;
    public static final int SCORES_BUTTON_WIDTH = 150;
    public static final int SCORES_BUTTON_HEIGHT = 80;
    public static final int EXIT_BUTTON_WIDTH = 150;
    public static final int EXIT_BUTTON_HEIGHT = 80;
    public static final int START_BUTTON_Y = 450;
    public static final int PROFILE_BUTTON_Y = 350;
    public static final int SCORES_BUTTON_Y = 250;
    public static final int EXIT_BUTTON_Y = 150;

    int start_x = QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2;
    int profile_x = QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2;
    int scores_x = QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2;
    int exit_x = QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;


    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture profileBtn;
    private Texture scoresBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, QuizGame.WIDTH / 2, QuizGame.HEIGHT / 2);
//        background = new Texture("bg.png");
        playBtn = new Texture(Gdx.files.internal("startbutton.png"));
        exitBtn = new Texture(Gdx.files.internal("exitbutton.png"));
        profileBtn = new Texture(Gdx.files.internal("profilebutton.png"));
        scoresBtn = new Texture(Gdx.files.internal("scoresbutton.png"));
    }

    @Override
    public void handleInput() {
        if(Gdx.input.getX() < start_x + START_BUTTON_WIDTH && Gdx.input.getX() > start_x && QuizGame.HEIGHT - Gdx.input.getY() < START_BUTTON_Y + START_BUTTON_HEIGHT && QuizGame.HEIGHT - Gdx.input.getY() > START_BUTTON_Y) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
//        sb.draw(background, 0,0);
        sb.draw(playBtn, QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        sb.draw(profileBtn, QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2, PROFILE_BUTTON_Y, PROFILE_BUTTON_WIDTH, PROFILE_BUTTON_HEIGHT);
        sb.draw(scoresBtn, QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2, SCORES_BUTTON_Y, SCORES_BUTTON_WIDTH, SCORES_BUTTON_HEIGHT);
        sb.draw(exitBtn, QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
//        background.dispose();
        playBtn.dispose();
//        exitBtn.dispose();
//        profileBtn.dispose();
//        scoresBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
