package com.brentaureli.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by brentaureli on 7/15/15.
 */
public class Option {
    public static final int TUBE_WIDTH = 52;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;


    public Option(float y){
        topTube = new Texture("tube.png");
        bottomTube = new Texture("tube.png");
        rand = new Random();


        posTopTube = new Vector2(-50, y);
        posBotTube = new Vector2(250, y);

        if(rand.nextDouble()>0.5){
            topTube = new Texture("tube2.png");
            boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
            boundsBot = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        }
        else{
            bottomTube = new Texture("tube2.png");
            boundsTop = new Rectangle(posBotTube.x,posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
            boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
        }
    }


    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x){
        posTopTube.set(0, x);
        posBotTube.set(Gdx.graphics.getWidth(), x);
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
