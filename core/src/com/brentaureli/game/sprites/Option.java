package com.brentaureli.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.brentaureli.game.questions.Question;

import java.util.Random;

public class Option {

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Question question;
    private Random rand;


    public Option(float y, Question question) {
        this.question = question;
        topTube = new Texture("tube.png");
        bottomTube = new Texture("tube.png");
        rand = new Random();


        posTopTube = new Vector2(0, y);
        posBotTube = new Vector2(250, y);

        if (question.getCorrectAnswer() == 2) {
            topTube = new Texture("tube2.png");
            boundsTop = new Rectangle(posTopTube.x, posTopTube.y, 250, topTube.getHeight());
            boundsBot = new Rectangle(posTopTube.x, posTopTube.y, 250, topTube.getHeight());
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

    public void setTopTube(Texture topTube) {
        this.topTube = topTube;
    }

    public void setBottomTube(Texture bottomTube) {
        this.bottomTube = bottomTube;
    }

    public void setPosTopTube(Vector2 posTopTube) {
        this.posTopTube = posTopTube;
    }

    public void setPosBotTube(Vector2 posBotTube) {
        this.posBotTube = posBotTube;
    }

    public Rectangle getBoundsTop() {
        return boundsTop;
    }

    public void setBoundsTop(Rectangle boundsTop) {
        this.boundsTop = boundsTop;
    }

    public Rectangle getBoundsBot() {
        return boundsBot;
    }

    public void setBoundsBot(Rectangle boundsBot) {
        this.boundsBot = boundsBot;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
