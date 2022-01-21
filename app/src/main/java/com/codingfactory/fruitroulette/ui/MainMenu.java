package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codingfactory.fruitroulette.R;

public class MainMenu extends AppCompatActivity {

    TextView quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

            quit=findViewById(R.id.quitButton);
            quit.setOnClickListener(view1 -> {
                finish();
                System.exit(0);
            });
    }

    public void highScores(View view) {
        Intent scoresIntent = new Intent(MainMenu.this, HighScores.class);
//        newIntent.putExtra();
        startActivity(scoresIntent);
    }

    public void newGame(View view) {
        Intent gameIntent = new Intent(MainMenu.this, NewGame.class);
//        newIntent.putExtra();
        startActivity(gameIntent);
    }

}