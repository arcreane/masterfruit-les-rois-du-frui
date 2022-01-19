package com.codingfactory.fruitroulette.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.codingfactory.fruitroulette.R;

public class HighScores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);

        TextView tV_NameFirst = findViewById(R.id.tV_Name1);
        TextView tV_NameSecond = findViewById(R.id.tV_Name2);
        TextView tV_ScoreFirst = findViewById(R.id.tV_Score1);
        TextView tV_ScoreSecond = findViewById(R.id.tV_Score2);





//        Bundle extras = getIntent().getExtras();
//        String url = extras.getString();

    }
}