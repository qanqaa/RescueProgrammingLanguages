package com.brentaureli.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.game.QuizGame;

public class StageState extends State {

//    // deklaracja wielkości buttonów
//    public static final int START_BUTTON_WIDTH = 150;
//    public static final int START_BUTTON_HEIGHT = 80;
//    public static final int PROFILE_BUTTON_WIDTH = 150;
//    public static final int PROFILE_BUTTON_HEIGHT = 80;
//    public static final int SCORES_BUTTON_WIDTH = 150;
//    public static final int SCORES_BUTTON_HEIGHT = 80;
//    public static final int EXIT_BUTTON_WIDTH = 150;
//    public static final int EXIT_BUTTON_HEIGHT = 80;
//
//    // dla funkcji draw()
//    public static final int START_BUTTON_Y = 450;
//    public static final int PROFILE_BUTTON_Y = 350;
//    public static final int SCORES_BUTTON_Y = 250;
//    public static final int EXIT_BUTTON_Y = 150;
//
//    // POŁOŻENIE BUTTONÓW
//
//    int start_x = QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2;
//    int profile_x = QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2;
//    int scores_x = QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2;
//    int exit_x = QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
//
//    //dla getX() / getY()
//    public static final int start_y = QuizGame.HEIGHT - START_BUTTON_Y - START_BUTTON_HEIGHT;
//    public static final int profile_y = QuizGame.HEIGHT - PROFILE_BUTTON_Y - PROFILE_BUTTON_HEIGHT;
//    public static final int scores_y = QuizGame.HEIGHT - SCORES_BUTTON_Y - SCORES_BUTTON_HEIGHT;
//    public static final int exit_y = QuizGame.HEIGHT - EXIT_BUTTON_Y - EXIT_BUTTON_HEIGHT;
//
//
//    private Texture background;
//    private Texture playBtn;
//    private Texture exitBtn;
//    private Texture profileBtn;
//    private Texture scoresBtn;

    public StageState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, QuizGame.WIDTH / 2, QuizGame.HEIGHT / 2);
//        background = new Texture("bg.png");
//        playBtn = new Texture(Gdx.files.internal("startbutton.png"));
//        exitBtn = new Texture(Gdx.files.internal("exitbutton.png"));
//        profileBtn = new Texture(Gdx.files.internal("profilebutton.png"));
//        scoresBtn = new Texture(Gdx.files.internal("scoresbutton.png"));
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
//            Rectangle start=new Rectangle(start_x,start_y,START_BUTTON_WIDTH,START_BUTTON_HEIGHT);
//            Rectangle profile=new Rectangle(profile_x,profile_y,PROFILE_BUTTON_WIDTH,PROFILE_BUTTON_HEIGHT);
//            Rectangle scores=new Rectangle(scores_x,scores_y,SCORES_BUTTON_WIDTH,SCORES_BUTTON_HEIGHT);
//            Rectangle exit=new Rectangle(exit_x,exit_y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
//            // texture x is the x position of the texture
//            // texture y is the y position of the texture
//            // texturewidth is the width of the texture (you can get it with texture.getWidth() or textureRegion.getRegionWidth() if you have a texture region
//            // textureheight is the height of the texture (you can get it with texture.getHeight() or textureRegion.getRegionhHeight() if you have a texture region
//            Gdx.app.log("x:", String.valueOf(tmp.x));
//            Gdx.app.log("y:", String.valueOf(tmp.y));
//            if(start.contains(tmp.x,tmp.y))
//            {
//                gsm.set(new PlayState(gsm));
//            }
//            else if(profile.contains(tmp.x,tmp.y))
//            {
//                // wczytaj nową scenę
//            }
//            else if(scores.contains(tmp.x,tmp.y))
//            {
//                // wczytaj nową scenę
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
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
//        sb.draw(background, 0,0);
//        sb.draw(playBtn, QuizGame.WIDTH / 2 - START_BUTTON_WIDTH / 2, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
//        sb.draw(profileBtn, QuizGame.WIDTH / 2 - PROFILE_BUTTON_WIDTH / 2, PROFILE_BUTTON_Y, PROFILE_BUTTON_WIDTH, PROFILE_BUTTON_HEIGHT);
//        sb.draw(scoresBtn, QuizGame.WIDTH / 2 - SCORES_BUTTON_WIDTH / 2, SCORES_BUTTON_Y, SCORES_BUTTON_WIDTH, SCORES_BUTTON_HEIGHT);
//        sb.draw(exitBtn, QuizGame.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
//        background.dispose();
//        playBtn.dispose();
//        exitBtn.dispose();
//        profileBtn.dispose();
//        scoresBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
