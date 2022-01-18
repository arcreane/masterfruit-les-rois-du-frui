package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.fruit.Fruit;
import com.codingfactory.fruitroulette.logic.GamePlay;

import java.util.ArrayList;


public class NewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.gameplay);

        Spinner firstChoice = findViewById(R.id.firstChoice);
        Spinner secondChoice = findViewById(R.id.secondChoice);
        Spinner thirdChoice = findViewById(R.id.thirdChoice);
        Spinner forthChoice = findViewById(R.id.forthChoice);

        ArrayList<String> fruitSelection = new ArrayList<>();
        fruitSelection.add("ic_strawberry");
        fruitSelection.add("ic_banana");
        fruitSelection.add("ic_raspberry");
        fruitSelection.add("ic_kiwi");
        fruitSelection.add("ic_orange");
        fruitSelection.add("ic_plum");
        fruitSelection.add("ic_grapes");
        fruitSelection.add("ic_lemon");

        SpinnerAdapter fruitAdapter = new SpinnerAdapter(getApplicationContext(), fruitSelection);
        fruitAdapter.setDropDownViewResource(R.layout.dropdown_fruit);
        firstChoice.setAdapter(fruitAdapter);
        secondChoice.setAdapter(fruitAdapter);
        thirdChoice.setAdapter(fruitAdapter);
        forthChoice.setAdapter(fruitAdapter);


    }
}