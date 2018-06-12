package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.brentaureli.game.commons.StageInfo;
import com.brentaureli.game.profiles.Profile;
import com.brentaureli.game.profiles.ProfileManager;
import com.brentaureli.game.questions.Question;
import com.brentaureli.game.questions.QuestionManager;
import com.brentaureli.game.scores.PlayerScore;
import com.brentaureli.game.scores.PlayerScoreManager;
import com.brentaureli.game.sprites.Option;
import com.brentaureli.game.sprites.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayState extends State {
    private Profile currentProfile;

    private boolean peripheralAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    private float xRot;
    private int gameWidth = Gdx.graphics.getWidth();
    private int gameHeight = Gdx.graphics.getHeight();
    private static final int OPTIONS_AMOUNT = 10;
    private int currentQuestion = 0;
    private int velocity = 100;
    private Player player;
    private QuestionManager questionManager = new QuestionManager();
    private List<Question> questionsForStage;
    private OrthographicCamera guiCam;
    private List<Option> options;
    private long timeSinceStart;
    private int score;
    private int stageInfoTimeSeconds = 5;
    private double timeForQuestion;
    private double timeBetweenQuestions;
    private double maxY;
    private float gameSpeed1s = velocity * 2.3f;
    private Texture background;
    private StageInfo stageInfo;
    Label.LabelStyle nameLabelStyle;
    Label label1;
    Label label1a;
    Label label2;
    Label label2a;
    Label questionText;
    Label answer1;
    Label answer2;
    Stage stage;
    GlyphLayout stageLayout;
    Table table;
    Table questionTable;
    Table scoreTable;

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont font40 ;
    private BitmapFont font80 ;
    private BitmapFont font20 ;
    private BitmapFont font120 ;
    private BitmapFont font100 ;


    public PlayState(GameStateManager gsm, StageInfo stageInfo) {
        super(gsm);
        background = new Texture(Gdx.files.internal("road.png"));
        this.stageInfo = stageInfo;
        timeSinceStart = System.currentTimeMillis();
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player( Gdx.graphics.getWidth()/ 2, 300);
        cam.setToOrtho(false, Gdx.graphics.getWidth(), gameWidth / 2);

        currentProfile = ProfileManager.getInstance().getCurrentProfile();
        timeForQuestion = currentProfile.getTimeForQuestion();
        timeBetweenQuestions = timeForQuestion / 4;

        questionsForStage = questionManager.prepareQuestionsForStage(stageInfo.getLevel());

        score = 0;

        options = setGameDifficulty();


        parameter.size = 120;
        font120 = generator.generateFont(parameter);

        parameter.size = 20;
        font20 = generator.generateFont(parameter);

        parameter.size = 40;
        font40 = generator.generateFont(parameter);

        parameter.size = 80;
        font80 = generator.generateFont(parameter);

        parameter.size = 100;
        font100 = generator.generateFont(parameter);

        nameLabelStyle = new Label.LabelStyle();
        nameLabelStyle.font = font100;

        label1 = new Label("", nameLabelStyle);
        label2 = new Label("", nameLabelStyle);
        label1a = new Label("", nameLabelStyle);
        label2a = new Label("", nameLabelStyle);
        answer1 = new Label("", nameLabelStyle);
        answer2 = new Label("", nameLabelStyle);
        questionText = new Label("", nameLabelStyle);
        stageLayout = new GlyphLayout();
        questionTable = new Table();
        questionTable.setFillParent(true);
        scoreTable = new Table();
        scoreTable.setFillParent(true);

        questionText.setWrap(true);
        answer2.setWrap(true);
        answer1.setWrap(true);
    }

    private List<Option> setGameDifficulty() {
        options = new ArrayList<>();
        for (int i = 0; i < OPTIONS_AMOUNT; i++) {
            options.add(new Option((float) (gameSpeed1s * (stageInfoTimeSeconds + (i + 1) * (timeForQuestion) + timeBetweenQuestions * i)), questionsForStage.get(i)));
        }
        maxY = gameSpeed1s * (stageInfoTimeSeconds + 10 * (timeForQuestion) + timeBetweenQuestions * 10);
        return options;
    }

    @Override
    protected void handleInput() {
//        if(Gdx.input.isKeyPressed(Input.Keys.A)){
//            player.moveLeft();
//         }
//        if(Gdx.input.isKeyPressed(Input.Keys.D)){
//                        player.moveRight();
//                    }
//                    player.jump();
    }

    private void updateScore() {
        score += stageInfo.getLevel() * calculateMultiplier();
    }

    private int calculateMultiplier() {
        return (int) Math.round(100 / (Math.pow(timeForQuestion, 2)));
    }

    @Override
    public void update(float dt) {

        if(peripheralAvailable) {
            xRot = Gdx.input.getAccelerometerX();
            Gdx.app.log("ACCELEROMETER", "" + xRot);
        }
            if (xRot < -1){
                player.moveRight();

            }
            else if (xRot > +1 ){
                player.moveLeft();

        }
        player.jump();


        handleInput();
        player.update(dt);

        cam.position.y = player.getPosition().y + velocity;

        for (int i = 0; i < options.size(); i++) {
            Option tube = options.get(i);

            if (tube.getPosTopTube().y - player.getPosition().y < 5) {
                updateScore();
            }
            if (tube.collides(player.getBounds()) || player.getPosition().y > maxY) {
                gsm.set(new EndGameState(gsm, score, checkPlayerHighScore(score)));
            }
        }
        cam.update();

    }

    private boolean checkPlayerHighScore(int score) {
        Map<Integer, Integer> currentProfileStageScoreMap = currentProfile.getStageScoreMap();
        if (score > currentProfileStageScoreMap.get(stageInfo.getLevel())) {
            currentProfileStageScoreMap.put(stageInfo.getLevel(), score);
            PlayerScoreManager.getInstance().addScore(new PlayerScore(currentProfile, calculateOverallScore()));
            return true;
        }
        PlayerScoreManager.getInstance().addScore(new PlayerScore(currentProfile, calculateOverallScore()));
        return false;
    }

    private int calculateOverallScore() {
        Map<Integer, Integer> stageScoreMap = currentProfile.getStageScoreMap();
        return stageScoreMap.values().stream().reduce(0, Integer::sum);
    }

    private void renderQuestionWithAnswers(BitmapFont font, SpriteBatch sb, Question question) {

        questionText.setText(question.getQuestion().toUpperCase());
        questionText.setAlignment(Align.center);
        questionText.setWrap(true);
        answer1.setText(question.getAnswers().get(0).toUpperCase());
        answer1.setWrap(true);
        answer1.setAlignment(Align.center);
        answer2.setText(question.getAnswers().get(1).toUpperCase());
        answer2.setAlignment(Align.center);
        answer2.setWrap(true);

        questionTable.top();
        questionTable.padTop(100);
        questionTable.add(questionText).colspan(2).fillX().expandX();
        questionTable.row();
        questionTable.add(answer1).expandX().width(Gdx.graphics.getWidth()/2-100);
        questionTable.add(answer2).expandX().width(Gdx.graphics.getWidth()/2-100);


        stage.addActor(questionTable);
    }

    @Override
    public void render(SpriteBatch sb) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        sb.setProjectionMatrix(guiCam.combined);
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        for (Option option : options) {
            sb.draw(option.getTopTube(), option.getPosTopTube().x, option.getPosTopTube().y, gameWidth / 2, 0);
            sb.draw(option.getBottomTube(), option.getPosBotTube().x, option.getPosBotTube().y, gameWidth / 2, 0);
        }

        sb.end();


        sb.setProjectionMatrix(guiCam.combined);
        sb.begin();
        if (System.currentTimeMillis() - timeSinceStart < stageInfoTimeSeconds * 1000) {
            setStageTextOpacity(font120, System.currentTimeMillis() - timeSinceStart);
            stageLayout.setText(font120, stageInfo.getStageName());
            float width = stageLayout.width;
            float height = stageLayout.height;
            font120.draw(sb, stageInfo.getStageName(), gameWidth / 2 - width / 2, gameHeight / 4 - height / 2);
            font120.setColor(1, 1, 1, 1);
        }
        if (currentQuestion == 0) {
            if (player.getPosition().y > gameSpeed1s * stageInfoTimeSeconds && player.getPosition().y + gameHeight / 6 < options.get(currentQuestion).getPosTopTube().y) {
                renderQuestionWithAnswers(font40, sb, options.get(currentQuestion).getQuestion());
            } else if (player.getPosition().y > options.get(currentQuestion).getPosTopTube().y) {
                currentQuestion++;
            }
        } else {
            if (currentQuestion < OPTIONS_AMOUNT) {
                if (player.getPosition().y > (options.get(currentQuestion - 1).getPosTopTube().y + timeBetweenQuestions * gameSpeed1s) && player.getPosition().y + gameHeight / 6 < options.get(currentQuestion).getPosTopTube().y) {
                    renderQuestionWithAnswers(font40, sb, options.get(currentQuestion).getQuestion());
                } else if (player.getPosition().y > options.get(currentQuestion).getPosTopTube().y) {
                    currentQuestion++;
                }
            }
        }


        sb.end();

        label1.setText("SCORE: ");
        label1a.setText("" + score);
        label2.setText("YOUR BEST: ");
        label2a.setText("" + currentProfile.getStageScoreMap().get(stageInfo.getLevel()));

        scoreTable.bottom();
        scoreTable.add(label1).expandX();
        scoreTable.add(label2).expandX();
        scoreTable.row();
        scoreTable.add(label1a).expandX();
        scoreTable.add(label2a).expandX();

        stage.addActor(scoreTable);

        stage.draw();
        stage.act();
    }


    private void setStageTextOpacity(BitmapFont font, long timeDifference) {
        long stageInfoTimeMax = 1000 * stageInfoTimeSeconds;
        float opacityChange = 2f / stageInfoTimeMax;
        if (timeDifference < (stageInfoTimeMax / 2)) {
            font.setColor(1, 1, 1, 1);
        } else {
            float opacity = 1f - ((timeDifference - stageInfoTimeMax / 2) * opacityChange);
            font.setColor(1, 1, 1, opacity);
        }
    }



    @Override
    public void dispose() {
        player.dispose();
        for (Option option : options) {
            option.dispose();
        }
        font20.dispose();
        font40.dispose();
        font80.dispose();
        font120.dispose();
        background.dispose();
        stage.dispose();

    }
}
