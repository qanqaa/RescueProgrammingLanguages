package com.brentaureli.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.brentaureli.game.states.PlayState;

public class Player {
    private int MOVEMENT = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation playerAnimation;
    private Texture texture;

    public Player(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("playeranimation.png");
        playerAnimation = new Animation(new TextureRegion(texture), 3, 1f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
    }

    public void update(float dt){
        playerAnimation.update(dt);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        MOVEMENT=0;
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return playerAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 220;
    }

    public void moveLeft(){
        if (position.x > 0) {
            MOVEMENT = -1000;
        }
    }
    public void moveRight(){
        if (position.x + bounds.width < Gdx.graphics.getWidth()) {
            MOVEMENT = 1000;
        }
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }

}