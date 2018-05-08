package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.game.QuizGame;

public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture profileBtn;
    private Texture scoresBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, QuizGame.WIDTH / 2, QuizGame.HEIGHT / 2);
//        background = new Texture("bg.png");
        playBtn = new Texture("startbutton.png");
        exitBtn = new Texture("exitbutton.png");
        profileBtn = new Texture("profilebutton.png");
        scoresBtn = new Texture("scoresbutton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
//        sb.draw(background, 0,0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y+playBtn.getHeight());
//        sb.draw(profileBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
//        sb.draw(scoresBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y-profileBtn.getHeight());
//        sb.draw(exitBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y-profileBtn.getHeight()-scoresBtn.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
//        background.dispose();
        playBtn.dispose();
        exitBtn.dispose();
        profileBtn.dispose();
        scoresBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
