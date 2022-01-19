package com.codingfactory.fruitroulette.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.codingfactory.fruitroulette.Fruits.*;
import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.fruit.Fruity;
import com.codingfactory.fruitroulette.logic.Game;
import com.codingfactory.fruitroulette.logic.GameRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NewGame extends AppCompatActivity {

<<<<<<< HEAD
    private ArrayList<String[]> guesses;
    private Spinner firstChoice, secondChoice, thirdChoice, fourthChoice;
    private Spinner[] choices;
=======
    private ArrayList<ArrayList<Fruit>> guesses;
    private Spinner firstChoice;
    private Spinner secondChoice;
    private Spinner thirdChoice;
    private Spinner fourthChoice;
>>>>>>> 23a333655c91dc6327b222ecc1fda7891487a13e
    private RecyclerAdapter adapter;
    private ProgressBar pB_Attempt;
    private Button b_Guess;

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

        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};

        Arrays.stream(choices).sequential().forEach(e -> e.setAdapter(fruitAdapter));

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
        Game game = new Game();
        game.launchGame();

        guesses = new ArrayList<>();

        ArrayList<Fruit> firstRow = new ArrayList<>();
        firstRow.add(new Banana());
        firstRow.add(new Kiwi());
        firstRow.add(new Lemon());
        firstRow.add(new Grape());

        guesses.add(firstRow);

//        Arrays.stream(choices).sequential().forEach(e -> e.setOnItemSelectedListener(toastMessage));

        RecyclerView guessView = findViewById(R.id.guessView);
        adapter = new RecyclerAdapter(this);
        adapter.setGuesses(guesses);
        guessView.setAdapter(adapter);
        guessView.setLayoutManager(new LinearLayoutManager(this));
<<<<<<< HEAD
        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};
    }

    public void addGuess(View view) {
        if (emptyFields()) {
            Toast.makeText(getApplicationContext(), "Uh oh, some fruit is missing!", Toast.LENGTH_SHORT).show();
        } else if (distinctChoices()) {
            int firstFruit = firstChoice.getSelectedItemPosition();
            int secondFruit = secondChoice.getSelectedItemPosition();
            int thirdFruit = thirdChoice.getSelectedItemPosition();
            int fourthFruit = fourthChoice.getSelectedItemPosition();

            guesses.add(new String[]{
                    Fruity.getFruitImg(firstFruit),
                    Fruity.getFruitImg(secondFruit),
                    Fruity.getFruitImg(thirdFruit),
                    Fruity.getFruitImg(fourthFruit)});
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Uh oh, no two fruits can be the same!",Toast.LENGTH_SHORT).show();
        }
=======
//        guessView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void addGuess(View view) {
        int firstFruit = firstChoice.getSelectedItemPosition();
        int secondFruit = secondChoice.getSelectedItemPosition();
        int thirdFruit = thirdChoice.getSelectedItemPosition();
        int fourthFruit = fourthChoice.getSelectedItemPosition();

        int[] choices = new int[]{firstFruit, secondFruit, thirdFruit, fourthFruit};
        ArrayList<Fruit> attempt = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            switch (choices[i]) {
                case 1:
                    attempt.add(new Strawberry());
                    break;
                case 2:
                    attempt.add(new Banana());
                    break;
                case 3:
                    attempt.add(new Raspberry());
                    break;
                case 4:
                    attempt.add(new Kiwi());
                    break;
                case 5:
                    attempt.add(new Orange());
                    break;
                case 6:
                    attempt.add(new Plum());
                    break;
                case 7:
                    attempt.add(new Grape());
                    break;
                case 8:
                    attempt.add(new Lemon());
                    break;
            }
        }
//        GameRound = new GameRound(attempt);
        

//        guesses.add(new String[]{
//                Fruity.getFruitImg(firstFruit),
//                Fruity.getFruitImg(secondFruit),
//                Fruity.getFruitImg(thirdFruit),
//                Fruity.getFruitImg(fourthFruit)
//        });
        adapter.notifyDataSetChanged();


        //ProgressBar action with Button
        b_Guess = findViewById(R.id.b_Guess);
        pB_Attempt = findViewById(R.id.pB_Attempt);

        b_Guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = pB_Attempt.getProgress();
                pB_Attempt.setProgress(++progress,true);




            }
        });

>>>>>>> 23a333655c91dc6327b222ecc1fda7891487a13e
    }

    public boolean emptyFields() {
        for (Spinner s : choices) {
            if (s.getSelectedItemPosition() == 0) return true;
        }
        return false;
    }

    public boolean distinctChoices() {
        Set<Integer> s = new HashSet<Integer>();
        Arrays.stream(choices).sequential().forEach(e -> s.add(e.getSelectedItemPosition()));
        return (s.size() == 4);
        }
}
//