package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codingfactory.fruitroulette.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        TextView quit = findViewById(R.id.quitButton);
        quit.setOnClickListener(v -> finish());
    }

    public void highScores(View view) {
        Intent scoresIntent = new Intent(MainMenu.this, HighScores.class);
        startActivity(scoresIntent);
    }

    public void newGame(View view) {
        Intent gameIntent = new Intent(MainMenu.this, NewGame.class);
        startActivity(gameIntent);
    }
}