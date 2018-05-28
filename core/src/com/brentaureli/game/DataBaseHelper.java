package com.brentaureli.game;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "RPL.db"; //create a variable for our database
    private final String TABLE_NAME1 = "user_info"; //create a variable for table name with user info
    private final String TABLE_NAME2 = "quiz"; //create a variable for quiz name with questions and answers

    public_DataBaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int odlVersion, int newVersion) {

    }

}
