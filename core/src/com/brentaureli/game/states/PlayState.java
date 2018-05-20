package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.questions.Question;
import com.brentaureli.game.questions.QuestionManager;
import com.brentaureli.game.sprites.Option;
import com.brentaureli.game.sprites.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {
    private static final int TUBE_COUNT = 10;
    private static final int GROUND_Y_OFFSET = -50;
    BitmapFont font = new BitmapFont();
    private Player player;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private QuestionManager questionManager = new QuestionManager();
    List<Question> questionsForStage;
    private List<Option> options;

    //TODO: constructor with stages
    public PlayState(GameStateManager gsm, int stage) {
        super(gsm);
        player = new Player(50, 300);
        cam.setToOrtho(false, QuizGame.WIDTH / 2, QuizGame.HEIGHT / 2);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        questionsForStage = questionManager.prepareQuestionsForStage(stage);

        options = new ArrayList<>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            options.add(new Option((i + 1) * (1500), questionsForStage.get(i)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.moveRight();
        }
            player.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        player.update(dt);

        cam.position.y = player.getPosition().y + 100;

        for (int i = 0; i < options.size(); i++) {
            Option tube = options.get(i);

//            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
//                tube.reposition(tube.getPosTopTube().x  + ((Option.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
//            }

            if(tube.collides(player.getBounds()))
                gsm.set(new MenuState(gsm));
        }

        if(player.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new MenuState(gsm));
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        int i = 0;
        for (Option option : options) {
            sb.draw(option.getTopTube(), option.getPosTopTube().x, option.getPosTopTube().y);
            sb.draw(option.getBottomTube(), option.getPosBotTube().x, option.getPosBotTube().y);
            Question question = option.getQuestion();
            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(font, question.getQuestion());
            float w = glyphLayout.width;
            font.draw(sb, glyphLayout, (500 - w) / 2, option.getPosTopTube().y - 200);
            font.draw(sb, question.getAnswers().get(0), option.getPosTopTube().x, option.getPosTopTube().y);
            font.draw(sb, question.getAnswers().get(1), option.getPosBotTube().x, option.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        player.dispose();
        ground.dispose();
        for (Option tube : options)
            tube.dispose();
        font.dispose();
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
