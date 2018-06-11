package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.profiles.ProfileManager;

public class ProfileState extends State {
    Preferences prefs = Gdx.app.getPreferences("profile");
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private int gameWidth = Gdx.graphics.getWidth();
    private int gameHeight = Gdx.graphics.getHeight();
    private BitmapFont font;
    private String nameText = ProfileManager.getInstance().getCurrentProfile().getName();
    private String questionsTimeFieldtext = String.valueOf(ProfileManager.getInstance().getCurrentProfile().getTimeForQuestion());
    private TextField nameField = new TextField(nameText, skin);

    private TextField questionsTimeField = new TextField(questionsTimeFieldtext, skin);
    private Texture background;
    private Texture photo;

    private String photoPath = "photos/";
    private final static int PHOTO_WIDTH = 800;
    private final static int PHOTO_HEIGHT = 800;

    private Texture exitBtn;


    private TextureRegion exitTextureRegion;
    private TextureRegionDrawable exitTexRegionDrawable;
    private ImageButton exitButton;
    Stage stage;



// TODO: font in textfield
//dla getX() / getY()




    public ProfileState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        background = new Texture(Gdx.files.internal("profilebg.png"));
        exitBtn = new Texture("exitbutton.png");

        exitTextureRegion = new TextureRegion(exitBtn);
        exitTexRegionDrawable = new TextureRegionDrawable(exitTextureRegion);
        exitButton = new ImageButton(exitTexRegionDrawable); //Set the button up

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui

        Label.LabelStyle nameLabelStyle = new Label.LabelStyle();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 120;
        BitmapFont font12 = generator.generateFont(parameter);
        nameLabelStyle.font = font12;


        Label label1 = new Label("NAME: ",nameLabelStyle);
        Label label2 = new Label("QUESTIONS TIME: ",nameLabelStyle);

        parameter.size = 80;
        font12 = generator.generateFont(parameter);
        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font = font12;


        Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.add(label1).right();
        table.add(nameField).minWidth(500).padBottom(50).padLeft(20).height(120);
        table.row().height(120);
        table.add(label2).right().padBottom(100);
        table.add(questionsTimeField).minWidth(500).padBottom(100).padLeft(20);
        table.row();
        table.add(exitButton).colspan(2);
        table.row();

        stage.addActor(table);

        exitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                String name = nameField.getText();
                float time = Float.parseFloat(questionsTimeField.getText());
                ProfileManager.getInstance().updateCurrentProfile(name, time);
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new MenuState(gsm));
            }
        });



    }

    @Override
    public void handleInput() {

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
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        String photoName = ProfileManager.getInstance().getCurrentProfile().getPhoto();
        //TODO: Add PhotoManager to get profile's photo or default photo
        if (photoName != null) {
            photo = new Texture(Gdx.files.internal(photoPath + photoName));
        } else {
            photo = new Texture(Gdx.files.internal(photoPath + "defaultPhoto.png"));
        }
        sb.draw(photo, gameWidth / 2 - PHOTO_WIDTH / 2, gameHeight / 2 - PHOTO_HEIGHT / 4, PHOTO_WIDTH, PHOTO_HEIGHT);
        sb.end();

        stage.draw();
        stage.act();

    }

    @Override
    public void dispose() {

        font.dispose();
        exitBtn.dispose();
        background.dispose();
        stage.dispose();
        photo.dispose();
    }
}
