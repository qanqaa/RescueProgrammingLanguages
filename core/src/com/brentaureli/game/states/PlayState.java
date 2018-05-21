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
        timeBetweenQuestions = timeForQuestion / 2;

        questionsForStage = questionManager.prepareQuestionsForStage(stage);

        score = 0;

        options = setGameDifficulty();
    }

    private List<Option> setGameDifficulty() {
        options = new ArrayList<>();
        for (int i = 0; i < OPTIONS_AMOUNT; i++) {
            float gameSpeed1s = velocity * 2.2f;
            options.add(new Option((float) (gameSpeed1s * (stageInfoTimeSeconds + (i + 1) * (timeForQuestion) + timeBetweenQuestions * i)), questionsForStage.get(i)));
        }
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
        score += 1;
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
            if (tube.collides(player.getBounds())) {
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

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);

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

        sb.end();
        sb.setProjectionMatrix(guiCam.combined);
        sb.begin();
        if (System.currentTimeMillis() - timeSinceStart < stageInfoTimeSeconds * 1000) {
            setStageOpacity(font, System.currentTimeMillis() - timeSinceStart);
            GlyphLayout stageLayout = new GlyphLayout();
            stageLayout.setText(font, "STAGE" + stage);
            float width = stageLayout.width;
            float height = stageLayout.height;
            font.getData().setScale(4, 4);
            font.draw(sb, "STAGE " + stage, gameWidth / 2 - width * 2, gameHeight / 2 - height * 2);
            font.setColor(1, 1, 1, 1);
        }
        font.getData().setScale(1, 1);

        //TODO: center scores, position texts
        font.draw(sb, "SCORE:" + score, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 50);
        font.draw(sb, "YOUR BEST:" + currentProfile.getStageScoreMap().get(stage), Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 70);
        sb.end();
    }


    private void setStageOpacity(BitmapFont font, long timeDifference) {
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
