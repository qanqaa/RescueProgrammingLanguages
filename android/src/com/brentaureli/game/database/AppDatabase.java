package com.brentaureli.game.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.brentaureli.game.DatabaseInterface;
import com.brentaureli.game.profiles.Profile;
import com.brentaureli.game.scores.PlayerScore;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Highscore.class, Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase implements DatabaseInterface {
    private static AppDatabase INSTANCE;

    public abstract HighscoreDao highscoreDao();
    public abstract QuestionDao questionDao();

    public synchronized static AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "rpl")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).questionDao().insertAll(Question.populateData());
                            }
                        });
                    }
                })
                .build();
    }

    public void nukeDatabases() {
        highscoreDao().nuke();
        questionDao().nuke();
        questionDao().insertAll(Question.populateData());
    }

    @Override
    public List<com.brentaureli.game.questions.Question> getQuestions(int category) {
        nukeDatabases();
        List<Question> questions = INSTANCE.questionDao().getAll(category);
        List<com.brentaureli.game.questions.Question> gameQuestions = new ArrayList<com.brentaureli.game.questions.Question>();
        for(Question q : questions) {
            gameQuestions.add(new com.brentaureli.game.questions.Question(q.getQuestion(), ImmutableList.of(q.getAnswerA(),q.getAnswerB()),q.getCorrectAnswer()));
        }
        return gameQuestions;
    }

    @Override
    public List<PlayerScore> getHighscore() {
        List<Highscore> highscores = INSTANCE.highscoreDao().getAll();
        List<PlayerScore> playerScores = new ArrayList<PlayerScore>();
        for(Highscore h : highscores) {
            playerScores.add(new PlayerScore(new Profile(h.getName()), h.getScore()));
        }
        return playerScores;
    }

    @Override
    public void insertScore(PlayerScore score) {
        Highscore highscore = new Highscore();
        highscore.setName(score.getProfile().getName());
        highscore.setScore(score.getScore());
        INSTANCE.highscoreDao().insert(highscore);
    }

}