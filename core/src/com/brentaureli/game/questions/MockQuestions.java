package com.brentaureli.game.questions;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class MockQuestions {


    public static List<Question> prepare10Questions(){
        List<Question> questionList = new ArrayList<>();

        Question question1 = new Question("Question 1, correct answer: A", ImmutableList.of("as1231dadad","as313dadada"),1);
        Question question2 = new Question("Question 2, correct answer: B", ImmutableList.of("as43dadad","asda31dada"),2);
        Question question3 = new Question("Question 3, correct answer: A", ImmutableList.of("asd252adad","a31sdadada"),1);
        Question question4 = new Question("Question 4, correct answer: B", ImmutableList.of("a4252sdadad","asdadada"),2);
        Question question5 = new Question("Question 5, correct answer: A", ImmutableList.of("asdadad","asdadada"),1);
        Question question6 = new Question("Question 6, correct answer: B", ImmutableList.of("asd2342adad","as13dadada"),2);
        Question question7 = new Question("Question 7, correct answer: A", ImmutableList.of("asdadad","asdadada"),1);
        Question question8 = new Question("Question 8, correct answer: B", ImmutableList.of("asd4242adad","as31dadada"),2);
        Question question9 = new Question("Question 9, correct answer: A", ImmutableList.of("asda424dad","asdadada"),1);
        Question question10 = new Question("Question 10, correct answer: B", ImmutableList.of("a4242sdadad","asdad`2da"),2);

        return ImmutableList.of(question1,question2,question3,question4,question5,question6,question7,question8,question9,question10);
    }

    public static List<Question> prepare20Questions(){
        Question question1 = new Question("Question 1, correct answer: A", ImmutableList.of("as1231dadad","as313dadada"),1);
        Question question2 = new Question("Question 2, correct answer: B", ImmutableList.of("as43dadad","asda31dada"),2);
        Question question3 = new Question("Question 3, correct answer: A", ImmutableList.of("asd252adad","a31sdadada"),1);
        Question question4 = new Question("Question 4, correct answer: B", ImmutableList.of("a4252sdadad","asdadada"),2);
        Question question5 = new Question("Question 5, correct answer: A", ImmutableList.of("asdadad","asdadada"),1);
        Question question6 = new Question("Question 6, correct answer: B", ImmutableList.of("asd2342adad","as13dadada"),2);
        Question question7 = new Question("Question 7, correct answer: A", ImmutableList.of("asdadad","asdadada"),1);
        Question question8 = new Question("Question 8, correct answer: B", ImmutableList.of("asd4242adad","as31dadada"),2);
        Question question9 = new Question("Question 9, correct answer: A", ImmutableList.of("asda424dad","asdadada"),1);
        Question question10 = new Question("Question 10, correct answer: B", ImmutableList.of("a4242sdadad","asdad`2da"),2);
        List<Question> questionList =ImmutableList.of(question1,question2,question3,question4,question5,question6,question7,question8,question9,question10);
        return questionList;
    }


}
