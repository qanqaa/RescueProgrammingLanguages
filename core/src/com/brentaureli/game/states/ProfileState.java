package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.QuizGame;
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
    private Texture background;
    private Texture photo;

    private String photoPath = "photos/";
    private final static int PHOTO_WIDTH = 800;
    private final static int PHOTO_HEIGHT = 800;

    private Texture exitBtn;

    private static final int ALL_BUTTONS_WIDTH = 300;
    private static final int ALL_BUTTONS_HEIGHT = 80;

    // dla funkcji draw()
    private static final int EXIT_BUTTON_Y = 10;

    private int ALL_BUTTONS_X = QuizGame.WIDTH / 2 - ALL_BUTTONS_WIDTH / 2;

    private TextureRegion exitTextureRegion;
    private TextureRegionDrawable exitTexRegionDrawable;
    private ImageButton exitButton;



    //TODO: AWT working on Android? And why is this awt
//dla getX() / getY()
    private static final int exit_y = QuizGame.HEIGHT - EXIT_BUTTON_Y - ALL_BUTTONS_HEIGHT;


    Stage stage = new Stage();
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
        //nameField = new TextField("",textFieldStyle);
        //questionsTimeField = new TextField(String.valueOf(ProfileManager.getInstance().getCurrentProfile().getTimeForQuestion()) , textFieldStyle);

        parameter.size = 80;
        font12 = generator.generateFont(parameter);
        TextField.TextFieldStyle textFieldStyle = skin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font = font12;
//        textFieldStyle.cursor = skin.newDrawable("cursor", Color.GREEN);
//        textFieldStyle.cursor.setMinHeight(80f);
//        nameField.setCursorPosition(10);


        //textFieldStyle.cursor.setMinWidth(60f);

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
                Gdx.app.log("CLIIIICK", tmp.x + " " + tmp.y);
                gsm.set(new MenuState(gsm));
            }
        });



    }

    @Override
    public void handleInput() {
//        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
//            gsm.set(new MenuState(gsm));
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
//            ProfileManager.getInstance().setCurrentProfile(new Profile(nameField.getText(), Double.parseDouble(questionsTimeField.getText())));
//            gsm.set(new MenuState(gsm));
//        }
//        if (Gdx.input.isTouched()) {
//            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            if (exit.contains(tmp.x, tmp.y)) {
//                gsm.set(new MenuState(gsm));
//            }
//        }
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

//        nameField.setPosition(gameWidth / 2, gameHeight - 580);
//        nameField.setSize(750, 200);
//        questionsTimeField.setPosition(gameWidth / 2, gameHeight - 630);
//        questionsTimeField.setSize(750, 200);
//        stage.addActor(nameField);
//        stage.addActor(questionsTimeField);
//        Gdx.input.setInputProcessor(stage);
        stage.draw();
        stage.act();

    }

    @Override
    public void dispose() {
        font.dispose();
        background.dispose();
    }
}
