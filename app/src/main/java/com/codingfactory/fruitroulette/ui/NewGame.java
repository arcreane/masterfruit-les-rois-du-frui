package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.fruit.Fruity;
import com.codingfactory.fruitroulette.logic.GameSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewGame extends AppCompatActivity {

    private ArrayList<Integer[]> guesses;
    private Spinner firstChoice, secondChoice, thirdChoice, fourthChoice;
    private Spinner[] choices;
    private RecyclerAdapter adapter;
    private GameSequence game;
    private ProgressBar pB_Attempt;
    private Button b_Guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.gameplay);
        GameSequence game = new GameSequence();

        firstChoice = findViewById(R.id.first);
        secondChoice = findViewById(R.id.second);
        thirdChoice = findViewById(R.id.third);
        fourthChoice = findViewById(R.id.fourth);

        List<String> fruitImgs = new ArrayList<>();
        game.getPossibleFruit().stream().sorted().forEach(e -> fruitImgs.add(e.getImg()));

        SpinnerAdapter fruitAdapter = new SpinnerAdapter(getApplicationContext(), fruitImgs);
        fruitAdapter.setDropDownViewResource(R.layout.dropdown_fruit);

        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};

        Arrays.stream(choices).sequential().forEach(e -> e.setAdapter(fruitAdapter));

        guesses = new ArrayList<>();
        Button guessButton = findViewById(R.id.b_Guess);

        guessButton.setOnClickListener(view -> {
            if (emptyFields()) {
                Toast.makeText(getApplicationContext(), "Uh oh, some fruit is missing!", Toast.LENGTH_SHORT).show();
            } else if (distinctChoices()) {
                int firstFruit = firstChoice.getSelectedItemPosition()-1;
                int secondFruit = secondChoice.getSelectedItemPosition()-1;
                int thirdFruit = thirdChoice.getSelectedItemPosition()-1;
                int fourthFruit = fourthChoice.getSelectedItemPosition()-1;
                System.out.println("Guessed 0: " + firstFruit);
                System.out.println("Guessed 1: " + secondFruit);
                System.out.println("Guessed 2: " + thirdFruit);
                System.out.println("Guessed 3: " + fourthFruit);

                int intArray[] = {firstFruit, secondFruit, thirdFruit, fourthFruit};
                game.makeAGuess(intArray);
                guesses.add(new Integer[] {firstFruit, secondFruit, thirdFruit, fourthFruit});
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "Uh oh, no two fruits can be the same!",Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView guessView = findViewById(R.id.guessView);
        adapter = new RecyclerAdapter(this, game);
        guessView.setAdapter(adapter);
        guessView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setGuesses(guesses);
        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};
    }

    public boolean emptyFields() {
        for (Spinner s : choices) {
            if (s.getSelectedItemPosition() == 0) return true;
        }
        return false;
    }

    public boolean distinctChoices() {
        Set<Integer> s = new HashSet<>();
        Arrays.stream(choices).sequential().forEach(e -> s.add(e.getSelectedItemPosition()));
        return (s.size() == 4);
        }

    public void getFirstHint(View view) {
        if (!game.canIGetAHint(2)) {
            Toast.makeText(getApplicationContext(), "Uh oh, not enough points!",Toast.LENGTH_SHORT).show();
        } else {
            game.getFirstHint();
        }
    }
}
