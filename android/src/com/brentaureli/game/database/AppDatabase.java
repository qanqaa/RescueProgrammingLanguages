package com.brentaureli.game.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.brentaureli.game.DatabaseInterface;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase implements DatabaseInterface {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
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

    @Override
    public List<com.brentaureli.game.questions.Question> getQuestions() {
        List<Question> questions = INSTANCE.questionDao().getAll();
        List<com.brentaureli.game.questions.Question> gameQuestions = new ArrayList<com.brentaureli.game.questions.Question>();
        for(Question q : questions) {
            gameQuestions.add(new com.brentaureli.game.questions.Question(q.getQuestion(), ImmutableList.of(q.getAnswerA(),q.getAnswerB()),q.getCorrectAnswer()));
        }

        return gameQuestions;
    }

}