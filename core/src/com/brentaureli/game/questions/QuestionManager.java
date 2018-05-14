package com.brentaureli.game.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class QuestionManager {

    private final static int STAGE_QUESTION_AMOUNT = 10;


    public List<Question> prepareQuestionsForStage(int stage){
//        TODO: implement database with questions
        //List<Question> allQuestions = questionService.getAllQuestionsForStage(stage);

        List<Question> allQuestions = MockQuestions.prepare20Questions();
        List<Question> stageQuestions = new ArrayList<>();
        Random random = new Random();
        if(allQuestions.size()<=10){
            return allQuestions;
        }
        IntStream.rangeClosed(1,STAGE_QUESTION_AMOUNT).forEach(i-> {
            int questionIndex = random.nextInt(allQuestions.size());
            Question questionToAdd = allQuestions.get(questionIndex);
            stageQuestions.add(questionToAdd);
            stageQuestions.remove(questionIndex);
        });

        return stageQuestions;
    }

}
