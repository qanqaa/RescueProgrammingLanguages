package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.scores.PlayerScore;
import com.brentaureli.game.scores.PlayerScoreManager;

import java.util.List;

public class ScoresState extends State {


    private List<PlayerScore> playerScoreList;
    private BitmapFont font;
    private Texture background;
    private Texture defaultPhoto;
    private Texture photo;
    private String photosFolder = "photos/";

    private Texture exitBtn;


    private TextureRegion exitTextureRegion;
    private TextureRegionDrawable exitTexRegionDrawable;
    private ImageButton exitButton;

    private Stage stage;



    public ScoresState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        background = new Texture(Gdx.files.internal("highscoresbg.png"));
        exitBtn = new Texture("exitbutton.png");
        playerScoreList = PlayerScoreManager.getInstance().getHighscores();
        if (playerScoreList.size() > 10) {
            playerScoreList.subList(10, playerScoreList.size()).clear();
        }
        defaultPhoto = new Texture(Gdx.files.internal(photosFolder + "defaultPhoto.png"));



        exitTextureRegion = new TextureRegion(exitBtn);
        exitTexRegionDrawable = new TextureRegionDrawable(exitTextureRegion);
        exitButton = new ImageButton(exitTexRegionDrawable); //Set the button up

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        Table table = new Table();
        table.setFillParent(true);
        table.bottom();

        table.add(exitButton);
        table.row();

        stage.addActor(table);

        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new MenuState(gsm));
            }
        });
    }

    @Override
    public void handleInput() {
        // FOR DESKTOP
//        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
//            gsm.set(new MenuState(gsm));
//        }
//        if (Gdx.input.isTouched()) {
//            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            if (exit.contains(tmp.x, tmp.y)) {
//                gsm.set(new MenuState(gsm));
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
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        GlyphLayout layout = new GlyphLayout();
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        int nameWidth = width / 6;
        int scoreWidth = (2 * width / 3)+180;
        int photoWidth = nameWidth - 50;


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 120;
        BitmapFont font12 = generator.generateFont(parameter);
        height -= 800;
        //TODO: photo Manager
        for (PlayerScore playerScore : playerScoreList) {
            if (playerScore.getProfile().getPhoto() != null) {
                photo = new Texture(Gdx.files.internal(photosFolder + playerScore.getProfile().getPhoto()));
            } else {
                photo = defaultPhoto;
            }
            height -= 125;
            font12.draw(sb, playerScore.getProfile().getName(), nameWidth+15, height);
            font12.draw(sb, String.valueOf(playerScore.getScore()), scoreWidth+40, height);
            sb.draw(photo, photoWidth-100, height - 100, 100, 100);
        }
        sb.end();

        stage.draw();
        stage.act();

        generator.dispose();
        font12.dispose();

    }

    @Override
    public void dispose() {
        exitBtn.dispose();
        defaultPhoto.dispose();
        photo.dispose();
        font.dispose();
        photo.dispose();
        defaultPhoto.dispose();
        background.dispose();
        stage.dispose();
    }
}
