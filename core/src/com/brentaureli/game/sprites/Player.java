package com.brentaureli.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Brent on 7/5/2015.
 */
public class Player {
    private int MOVEMENT = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;

    public Player(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 1f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);
        velocity.scl(dt);
        position.add(MOVEMENT, velocity.y, 0);
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
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 220;
    }

    public void moveLeft(){
        MOVEMENT = -10;
    }
    public void moveRight(){
        MOVEMENT = 10;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }

}