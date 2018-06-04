package com.brentaureli.game.android.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Bonk on 2018-06-03.
 */
@Entity(tableName = "users")
public class User {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_photo")
    private String photoName;
    @ColumnInfo(name = "user_highscore")
    private int stageScore;
    @ColumnInfo(name = "user_score_level1")
    private int stage1Score;
    @ColumnInfo(name = "user_score_level2")
    private int stage2Score;
    @ColumnInfo(name = "user_score_level3")
    private int stage3Score;
    @ColumnInfo(name = "time_for_question")
    private int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public int getStageScore() {
        return stageScore;
    }

    public void setStageScore(int stageScore) {
        this.stageScore = stageScore;
    }

    public int getStage1Score() {
        return stage1Score;
    }

    public void setStage1Score(int stage1Score) {
        this.stage1Score = stage1Score;
    }

    public int getStage2Score() {
        return stage2Score;
    }

    public void setStage2Score(int stage2Score) {
        this.stage2Score = stage2Score;
    }

    public int getStage3Score() {
        return stage3Score;
    }

    public void setStage3Score(int stage3Score) {
        this.stage3Score = stage3Score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
