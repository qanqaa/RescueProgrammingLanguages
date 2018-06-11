package com.brentaureli.game.questions;

import com.brentaureli.game.QuizGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class QuestionManager {

    private final static int STAGE_QUESTION_AMOUNT = 10;


    public List<Question> prepareQuestionsForStage(int stage){
        List<Question> allQuestions = QuizGame.getInstance().database.getQuestions(stage);

        List<Question> stageQuestions = new ArrayList<>();
        Random random = new Random();
        if(allQuestions.size()<=10){
            return allQuestions;
        }
        IntStream.rangeClosed(1,STAGE_QUESTION_AMOUNT).forEach(i-> {
            int questionIndex = random.nextInt(allQuestions.size());
            Question questionToAdd = allQuestions.get(questionIndex);
            stageQuestions.add(questionToAdd);
            allQuestions.remove(questionIndex);
        });

        return stageQuestions;
    }

}
