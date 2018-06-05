package com.brentaureli.game.android.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "quiz")
public class Quiz {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "questions_for_stage1")
    private String questionsStage1;
    @ColumnInfo(name = "questions_for_stage2")
    private String questionsStage2;
    @ColumnInfo(name = "questions_for_stage3")
    private String questionsStage3;
    @ColumnInfo(name = "answer_A")
    private String answerA;
    @ColumnInfo(name = "answer_B")
    private String answerB;
    @ColumnInfo(name = "correct_Answer")
    private String correctAnswer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionsStage1() {
        return questionsStage1;
    }

    public void setQuestionsStage1(String questionsStage1) {
        this.questionsStage1 = questionsStage1;
    }

    public String getQuestionsStage2() {
        return questionsStage2;
    }

    public void setQuestionsStage2(String questionsStage2) {
        this.questionsStage2 = questionsStage2;
    }

    public String getQuestionsStage3() {
        return questionsStage3;
    }

    public void setQuestionsStage3(String questionsStage3) {
        this.questionsStage3 = questionsStage3;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
