package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codingfactory.fruitroulette.R;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
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
    public void quit(View view) {
        Intent quitIntent = new Intent(MainMenu.this, Quit.class);
//        newIntent.putExtra();
        startActivity(quitIntent);
    }

    /*public int progressBarIncrement (){

    }*/
}