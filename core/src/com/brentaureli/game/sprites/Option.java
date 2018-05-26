package com.brentaureli.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.brentaureli.game.questions.Question;

public class Option {

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Question question;

    public Option(float y, Question question) {
        this.question = question;
        topTube = new Texture("tube.png");
        bottomTube = new Texture("tube.png");


        posTopTube = new Vector2(0, y);
        posBotTube = new Vector2(Gdx.graphics.getWidth() / 2, y);

        if (question.getCorrectAnswer() == 2) {
            topTube = new Texture("tube2.png");
            boundsTop = new Rectangle(posTopTube.x, posTopTube.y, Gdx.graphics.getWidth() / 2, 1);
            boundsBot = new Rectangle(posTopTube.x, posTopTube.y, Gdx.graphics.getWidth() / 2, 1);
        }
        else{
            bottomTube = new Texture("tube2.png");
            boundsTop = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), 1);
            boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), 1);
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

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
