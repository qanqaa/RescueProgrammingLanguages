package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.brentaureli.game.profiles.Profile;
import com.brentaureli.game.profiles.ProfileManager;

public class ProfileState extends State {
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private int gameWidth = Gdx.graphics.getWidth();
    private int gameHeight = Gdx.graphics.getHeight();
    private BitmapFont font;
    private String nameText = ProfileManager.getInstance().getCurrentProfile().getName();
    private String questionsTimeFieldtext = String.valueOf(ProfileManager.getInstance().getCurrentProfile().getTimeForQuestion());
    private TextField nameField = new TextField(nameText, skin);
    private TextField questionsTimeField = new TextField(questionsTimeFieldtext, skin);
    Stage stage = new Stage();
    public ProfileState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();


    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            ProfileManager.getInstance().setCurrentProfile(new Profile(nameField.getText(), Double.parseDouble(questionsTimeField.getText())));
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "CURRENT PROFILE");
        sb.begin();
//        font.getData().setScale(2, 2);
        font.draw(sb, "CURRENT PROFILE", (gameWidth / 2) - (layout.width / 2), gameHeight - 50);
        font.draw(sb, "NAME", gameWidth / 2 - layout.width, gameHeight - 150);
        font.draw(sb, "QUESTIONS TIME", gameWidth / 2 - layout.width, gameHeight - 200);
        sb.end();
        nameField.setPosition(gameWidth / 2, gameHeight - 180);
        nameField.setSize(150, 40);
        questionsTimeField.setPosition(gameWidth / 2, gameHeight - 230);
        questionsTimeField.setSize(50, 40);
        stage.addActor(nameField);
        stage.addActor(questionsTimeField);
        Gdx.input.setInputProcessor(stage);
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
