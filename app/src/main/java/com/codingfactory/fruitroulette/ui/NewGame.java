package com.codingfactory.fruitroulette.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.fruit.Fruity;

import java.util.ArrayList;
import java.util.List;


public class NewGame extends AppCompatActivity {

    private ArrayList<String[]> guesses;
    private Spinner firstChoice;
    private Spinner secondChoice;
    private Spinner thirdChoice;
    private Spinner fourthChoice;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.gameplay);

        firstChoice = findViewById(R.id.first);
        secondChoice = findViewById(R.id.second);
        thirdChoice = findViewById(R.id.third);
        fourthChoice = findViewById(R.id.fourth);

        List<String> fruitSelection = Fruity.getImgList();

        SpinnerAdapter fruitAdapter = new SpinnerAdapter(getApplicationContext(), fruitSelection);
        fruitAdapter.setDropDownViewResource(R.layout.dropdown_fruit);

        firstChoice.setAdapter(fruitAdapter);
        secondChoice.setAdapter(fruitAdapter);
        thirdChoice.setAdapter(fruitAdapter);
        fourthChoice.setAdapter(fruitAdapter);

        AdapterView.OnItemSelectedListener toastMessage = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String spinner = adapterView.getResources().getResourceEntryName(adapterView.getId());
                    Toast.makeText(getApplicationContext(), "Your " + spinner + " fruit is " +
                            Fruity.getFruitName(i) + ".",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        };

        //This will be moved to logic:
        guesses = new ArrayList<>();
        guesses.add(new String[]{"ic_banana", "ic_kiwi", "ic_raspberry", "ic_lemon"});
        guesses.add(new String[]{"ic_lemon", "ic_raspberry", "ic_grapes", "ic_orange"});

        firstChoice.setOnItemSelectedListener(toastMessage);
        secondChoice.setOnItemSelectedListener(toastMessage);
        thirdChoice.setOnItemSelectedListener(toastMessage);
        fourthChoice.setOnItemSelectedListener(toastMessage);

        RecyclerView guessView = findViewById(R.id.guessView);
        adapter = new RecyclerAdapter(this);
        adapter.setGuesses(guesses);
        guessView.setAdapter(adapter);
        guessView.setLayoutManager(new LinearLayoutManager(this));
//        guessView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }

    public void addGuess(View view) {
        int firstFruit = firstChoice.getSelectedItemPosition();
        int secondFruit = secondChoice.getSelectedItemPosition();
        int thirdFruit = thirdChoice.getSelectedItemPosition();
        int fourthFruit = fourthChoice.getSelectedItemPosition();

        guesses.add(new String[]{
                Fruity.getFruitImg(firstFruit),
                Fruity.getFruitImg(secondFruit),
                Fruity.getFruitImg(thirdFruit),
                Fruity.getFruitImg(fourthFruit)
        });
        adapter.notifyDataSetChanged();
    }
}