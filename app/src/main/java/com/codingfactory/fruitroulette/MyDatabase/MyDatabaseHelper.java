package com.codingfactory.fruitroulette.MyDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.codingfactory.fruitroulette.R;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    String TABLE_SCORE ="high_scores";
    String COLUMN_SCORE_ID ="score_id";
    String COLUMN_NAME = "name";
    String COLUMN_SCORE = "score";
    String COLUMN_ROUND = "round";
    private static final String DATABASE_NAME = "score_manager";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_SCORE + "("
                + COLUMN_SCORE_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
                + COLUMN_SCORE + " INTEGER, " + COLUMN_ROUND + " INTEGER )";
        db.execSQL(script);
        String init ="INSERT INTO " + TABLE_SCORE + " (name, score, round ) "+ "VALUES" + "('Didier', 20, 4) , ('Tomy',15, 2)";
        db.execSQL(init);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);

        // Recreate
        onCreate(db);
    }

    @SuppressLint("Range")
    public ArrayList getAllScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor res = db.rawQuery( "select * from "+TABLE_SCORE+" order by "+COLUMN_SCORE+ " desc " , null );
        res.moveToFirst();
        while(!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
            array_list.add(res.getString(res.getColumnIndex(COLUMN_SCORE)));
            array_list.add(res.getString(res.getColumnIndex(COLUMN_ROUND)));

            res.moveToNext();

        }
        return array_list;
    }

    public void addHighscore (String pseudo, int highscore, int round){
        SQLiteDatabase db = this.getWritableDatabase();
        String newScore = "INSERT INTO " + TABLE_SCORE + "("+ COLUMN_NAME +","+COLUMN_SCORE+"," +COLUMN_ROUND+")"+ " VALUES " + "("+ "'"+pseudo+"'"+ ","+ highscore+","+round+")";

        db.execSQL(newScore);

    }


}
