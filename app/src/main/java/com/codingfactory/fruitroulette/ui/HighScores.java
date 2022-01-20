package com.codingfactory.fruitroulette.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codingfactory.fruitroulette.R;

import java.util.ArrayList;

public class HighScores extends AppCompatActivity {

    private TableLayout scoreTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);

        scoreTable = findViewById(R.id.ScoreTable);
        HighScoreDisplay(highscoreTable);
    }

    ArrayList<String[]> highscoreTable = new ArrayList<>();
    //TableRow rowScore1 = findViewById(R.id.ScoreRow1);




    void HighScoreDisplay(ArrayList<String[]> highscoreTable) {

        String[] score1 = {"Thierry", "20"};
        String[] score2 = {"Bob", "10"};
        highscoreTable.add(score1);
        highscoreTable.add(score2);

        for (String[] rowScore : highscoreTable) {
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
}
