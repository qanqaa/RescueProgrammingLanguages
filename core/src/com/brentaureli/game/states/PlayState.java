package com.brentaureli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.game.QuizGame;
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

    private int stage;


    public PlayState(GameStateManager gsm, int stage) {
        super(gsm);
        this.stage = stage;
        timeSinceStart = System.currentTimeMillis();
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player(50, 300);
        cam.setToOrtho(false, QuizGame.WIDTH / 2, QuizGame.HEIGHT / 2);

        currentProfile = ProfileManager.getInstance().getCurrentProfile();
        timeForQuestion = currentProfile.getTimeForQuestion();
        timeBetweenQuestions = timeForQuestion / 4;

        questionsForStage = questionManager.prepareQuestionsForStage(stage);

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
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.moveRight();
        }
            player.jump();
    }

    private void updateScore() {
        score += stage * calculateMultiplier();
    }

    private int calculateMultiplier() {
        return (int) Math.round(100 / (Math.pow(timeForQuestion, 2)));
    }

    @Override
    public void update(float dt) {
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
        if (score > currentProfileStageScoreMap.get(stage)) {
            currentProfileStageScoreMap.put(stage, score);
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

    private void renderQuestionWithAnswers(BitmapFont font, SpriteBatch sb, Question question) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, question.getQuestion());
        float width = glyphLayout.width;
        float height = glyphLayout.height;
        font.draw(sb, glyphLayout, gameWidth / 2 - width / 2, gameHeight / 2 - height);
        glyphLayout.setText(font, question.getQuestion());
        font.draw(sb, question.getAnswers().get(0), gameWidth / 4 - glyphLayout.width / 4, gameHeight / 2 + glyphLayout.height);
        glyphLayout.setText(font, question.getQuestion());
        font.draw(sb, question.getAnswers().get(1), 3 * gameWidth / 4 - glyphLayout.width / 4, gameHeight / 2 + glyphLayout.height);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

        for (Option option : options) {
            sb.draw(option.getTopTube(), option.getPosTopTube().x, option.getPosTopTube().y, gameWidth / 2, 1);
            sb.draw(option.getBottomTube(), option.getPosBotTube().x, option.getPosBotTube().y, gameWidth / 2, 1);
        }

        sb.end();
        sb.setProjectionMatrix(guiCam.combined);
        sb.begin();
        if (System.currentTimeMillis() - timeSinceStart < stageInfoTimeSeconds * 1000) {
            setStageTextOpacity(font, System.currentTimeMillis() - timeSinceStart);
            GlyphLayout stageLayout = new GlyphLayout();
            stageLayout.setText(font, "STAGE" + stage);
            float width = stageLayout.width;
            float height = stageLayout.height;
            font.getData().setScale(4, 4);
            font.draw(sb, "STAGE " + stage, gameWidth / 2 - width * 2, gameHeight / 2 - height * 2);
            font.setColor(1, 1, 1, 1);
        }
        font.getData().setScale(2, 2);
        if (currentQuestion == 0) {
            if (player.getPosition().y > gameSpeed1s * stageInfoTimeSeconds && player.getPosition().y + gameHeight / 6 < options.get(currentQuestion).getPosTopTube().y) {
                renderQuestionWithAnswers(font, sb, options.get(currentQuestion).getQuestion());
            } else if (player.getPosition().y > options.get(currentQuestion).getPosTopTube().y) {
                currentQuestion++;
            }
        } else {
            if (currentQuestion < OPTIONS_AMOUNT) {
                if (player.getPosition().y > (options.get(currentQuestion - 1).getPosTopTube().y + timeBetweenQuestions * gameSpeed1s) && player.getPosition().y + gameHeight / 6 < options.get(currentQuestion).getPosTopTube().y) {
                    renderQuestionWithAnswers(font, sb, options.get(currentQuestion).getQuestion());
                } else if (player.getPosition().y > options.get(currentQuestion).getPosTopTube().y) {
                    currentQuestion++;
                }
            }
        }

        font.getData().setScale(1, 1);
        //TODO: center scores, position texts
        font.draw(sb, "SCORE:" + score, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 50);
        font.draw(sb, "YOUR BEST:" + currentProfile.getStageScoreMap().get(stage), Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 70);
        sb.end();
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
        for (Option option : options)
            option.dispose();
        font.dispose();
    }
}
