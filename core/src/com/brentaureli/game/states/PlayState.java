package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.sprites.Player;
import com.brentaureli.game.sprites.Option;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 10;
    private static final int GROUND_Y_OFFSET = -50;

    private Player player;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Option> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(50, 300);
        cam.setToOrtho(false, QuizGame.WIDTH / 2, QuizGame.HEIGHT / 2);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Option>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Option(i * (1500)));
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

        for(int i = 0; i < tubes.size; i++){
            Option tube = tubes.get(i);

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
        for(Option tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        player.dispose();
        ground.dispose();
        for(Option tube : tubes)
            tube.dispose();

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
