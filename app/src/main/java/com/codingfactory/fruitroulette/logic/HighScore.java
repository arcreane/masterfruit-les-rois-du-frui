package com.codingfactory.fruitroulette.logic;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codingfactory.fruitroulette.R;

import java.util.ArrayList;

public class HighScore extends AppCompatActivity {

    ArrayList <String[]> highscoreTable = new ArrayList<>();
    //TableRow rowScore1 = findViewById(R.id.ScoreRow1);
    TableLayout scoreTable = findViewById(R.id.ScoreTable);



    void HighScoreDisplay(ArrayList<String[]> highscoreTable){

        String[] score1 = {"Thierry", "20"};
        String[] score2 = {"Bob","10"};
        highscoreTable.add(score1);
        highscoreTable.add(score2);

        for(String[] rowScore : highscoreTable) {
            TableRow currentRow = new TableRow(getApplicationContext());
            TextView currentName = new TextView(getApplicationContext());
            TextView currentScore = new TextView(getApplicationContext());

            scoreTable.addView(currentRow);
            currentName.setText(rowScore[0]);
            currentScore.setText(rowScore[1]);

            currentRow.addView(currentName, 0);
            currentRow.addView(currentScore, 1);

            currentName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            currentScore.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        }
    }

/*    public class Row{
        private int iD;
        private TableRow tR;

        public Row(int iD, TableRow tR) {
            this.iD = iD;
            this.tR = new TableRow(getApplicationContext());
        }
    }*/



}
