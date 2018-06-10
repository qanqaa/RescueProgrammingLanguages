package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.brentaureli.game.QuizGame;
import com.brentaureli.game.commons.StageInfo;
import com.brentaureli.game.profiles.Profile;
import com.brentaureli.game.profiles.ProfileManager;
import com.brentaureli.game.questions.Question;
import com.brentaureli.game.questions.QuestionManager;
import com.brentaureli.game.scores.PlayerScore;
import com.brentaureli.game.scores.PlayerScoreManagerMock;
import com.brentaureli.game.sprites.Option;
import com.brentaureli.game.sprites.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayState extends State {
    private Profile currentProfile;

    private boolean peripheralAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);;
    private float xRot; // here
    private int gameWidth = Gdx.graphics.getWidth();
    private int gameHeight = Gdx.graphics.getHeight();
    private static final int OPTIONS_AMOUNT = 10;
    private int currentQuestion = 0;
    private BitmapFont font = new BitmapFont();
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

    // TODO: background, player, font, table?
    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Autobus-Bold.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont font12 = generator.generateFont(parameter);

    public PlayState(GameStateManager gsm, StageInfo stageInfo) {
        super(gsm);
        background = new Texture(Gdx.files.internal("playstatebg.png"));
        this.stageInfo = stageInfo;
        timeSinceStart = System.currentTimeMillis();
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player(50, 300);
        cam.setToOrtho(false, QuizGame.WIDTH, QuizGame.HEIGHT / 2);

        currentProfile = ProfileManager.getInstance().getCurrentProfile();
        timeForQuestion = currentProfile.getTimeForQuestion();
        timeBetweenQuestions = timeForQuestion / 4;

        questionsForStage = questionManager.prepareQuestionsForStage(stageInfo.getLevel());

        score = 0;

        options = setGameDifficulty();
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
            //Gdx.app.log("ACCELEROMETER", "NANANAN:  " + xRot);
        }

        if (xRot < -1){
            player.moveRight();
        }
        if (xRot > +1 ){
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
            PlayerScoreManagerMock.getInstance().updateScore(new PlayerScore(currentProfile, calculateOverallScore()));
            return true;
        }
        PlayerScoreManagerMock.getInstance().updateScore(new PlayerScore(currentProfile, calculateOverallScore()));
        return false;
    }

    private int calculateOverallScore() {
        Map<Integer, Integer> stageScoreMap = currentProfile.getStageScoreMap();
        return stageScoreMap.values().stream().reduce(0, Integer::sum);
    }

    private void renderQuestionWithAnswers(BitmapFont font12, SpriteBatch sb, Question question) {
        GlyphLayout glyphLayout = new GlyphLayout();
        parameter.size = 40;
        font12 = generator.generateFont(parameter);
        glyphLayout.setText(font12, question.getQuestion());
        float width = glyphLayout.width;
        float height = glyphLayout.height;



        font12.draw(sb, glyphLayout, gameWidth / 2 - width / 2, gameHeight / 2 - height);
        glyphLayout.setText(font, question.getQuestion());
        font12.draw(sb, question.getAnswers().get(0), gameWidth / 4 - glyphLayout.width / 4, gameHeight / 2 + glyphLayout.height);
        glyphLayout.setText(font, question.getQuestion());
        font12.draw(sb, question.getAnswers().get(1), 3 * gameWidth / 4 - glyphLayout.width / 4, gameHeight / 2 + glyphLayout.height);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(guiCam.combined);
        sb.begin();
        sb.draw(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (System.currentTimeMillis() - timeSinceStart < stageInfoTimeSeconds * 1000) {
            setStageTextOpacity(font12, System.currentTimeMillis() - timeSinceStart);
            GlyphLayout stageLayout = new GlyphLayout();
            stageLayout.setText(font12, stageInfo.getStageName());
            float width = stageLayout.width;
            float height = stageLayout.height;
            parameter.size = 120;
            font12 = generator.generateFont(parameter);
            font12.draw(sb, stageInfo.getStageName(), gameWidth / 2 - width * 2, gameHeight / 2 - height * 2);
            font12.setColor(1, 1, 1, 1);
        }
        parameter.size = 20;
        font12 = generator.generateFont(parameter);
        if (currentQuestion == 0) {
            if (player.getPosition().y > gameSpeed1s * stageInfoTimeSeconds && player.getPosition().y + gameHeight / 6 < options.get(currentQuestion).getPosTopTube().y) {
                renderQuestionWithAnswers(font12, sb, options.get(currentQuestion).getQuestion());
            } else if (player.getPosition().y > options.get(currentQuestion).getPosTopTube().y) {
                currentQuestion++;
            }
        } else {
            if (currentQuestion < OPTIONS_AMOUNT) {
                if (player.getPosition().y > (options.get(currentQuestion - 1).getPosTopTube().y + timeBetweenQuestions * gameSpeed1s) && player.getPosition().y + gameHeight / 6 < options.get(currentQuestion).getPosTopTube().y) {
                    renderQuestionWithAnswers(font12, sb, options.get(currentQuestion).getQuestion());
                } else if (player.getPosition().y > options.get(currentQuestion).getPosTopTube().y) {
                    currentQuestion++;
                }
            }
        }

        parameter.size = 35;
        font12 = generator.generateFont(parameter);
        //TODO: center scores, position texts
        font12.draw(sb, "SCORE: " + score, Gdx.graphics.getWidth()- 400, Gdx.graphics.getHeight() - 50);
        font12.draw(sb, "YOUR BEST: " + currentProfile.getStageScoreMap().get(stageInfo.getLevel()), Gdx.graphics.getWidth() - 220, Gdx.graphics.getHeight() - 50);
        sb.end();

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        for (Option option : options) {
            sb.draw(option.getTopTube(), option.getPosTopTube().x, option.getPosTopTube().y, gameWidth / 2, 1);
            sb.draw(option.getBottomTube(), option.getPosBotTube().x, option.getPosBotTube().y, gameWidth / 2, 1);
        }

        sb.end();
    }


    private void setStageTextOpacity(BitmapFont font12, long timeDifference) {
        long stageInfoTimeMax = 1000 * stageInfoTimeSeconds;
        float opacityChange = 2f / stageInfoTimeMax;
        if (timeDifference < (stageInfoTimeMax / 2)) {
            font12.setColor(1, 1, 1, 1);
        } else {
            float opacity = 1f - ((timeDifference - stageInfoTimeMax / 2) * opacityChange);
            font12.setColor(1, 1, 1, opacity);
        }
    }



    @Override
    public void dispose() {
        player.dispose();
        for (Option option : options)
            option.dispose();
        font12.dispose();
        background.dispose();
    }
}
