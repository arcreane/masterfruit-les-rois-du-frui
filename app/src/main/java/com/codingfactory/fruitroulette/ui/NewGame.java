package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private Spinner firstChoice, secondChoice, thirdChoice, fourthChoice;
    private Spinner[] choices;
    private RecyclerAdapter adapter;
    private GameSequence game;
    private ProgressBar pb_attempt;
    private Button b_Guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        setContentView(R.layout.gameplay);
        game = new GameSequence();

        firstChoice = findViewById(R.id.first);
        secondChoice = findViewById(R.id.second);
        thirdChoice = findViewById(R.id.third);
        fourthChoice = findViewById(R.id.fourth);
        pb_attempt = findViewById(R.id.pb_attempt);

        List<String> fruitImgs = new ArrayList<>();
        game.getPossibleFruit().stream().sorted().forEach(e -> fruitImgs.add(e.getImg()));

        SpinnerAdapter fruitAdapter = new SpinnerAdapter(getApplicationContext(), fruitImgs);
        fruitAdapter.setDropDownViewResource(R.layout.dropdown_fruit);

        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};

        Arrays.stream(choices).sequential().forEach(e -> e.setAdapter(fruitAdapter));

        Button guessButton = findViewById(R.id.b_Guess);
        pb_attempt.setMax(10);
        pb_attempt.setMin(0);
        pb_attempt.setProgress(10);

        guessButton.setOnClickListener(view -> {
            if (emptyFields()) {
                Toast.makeText(getApplicationContext(), "Uh oh, some fruit is missing!", Toast.LENGTH_SHORT).show();
            } else if (distinctChoices()) {
                int firstFruit = firstChoice.getSelectedItemPosition()-1;
                int secondFruit = secondChoice.getSelectedItemPosition()-1;
                int thirdFruit = thirdChoice.getSelectedItemPosition()-1;
                int fourthFruit = fourthChoice.getSelectedItemPosition()-1;
                int intArray[] = {firstFruit, secondFruit, thirdFruit, fourthFruit};
                game.makeAGuess(intArray);
                pb_attempt.setProgress(game.getAttempts(), true);
                System.out.println(game.getAttempts());
                System.out.println(pb_attempt.getX());

            } else {
                Toast.makeText(getApplicationContext(), "Uh oh, no two fruits can be the same!",Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView guessView = findViewById(R.id.guessView);
        adapter = new RecyclerAdapter(this, game);
        guessView.setAdapter(adapter);
        guessView.setLayoutManager(new LinearLayoutManager(this));
        game.setAdapter(adapter);
        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.seed_hint:
                if (game.canIGetAHint(2)) {
                    game.getFirstHint();
                    pb_attempt.setProgress(game.getAttempts(), true);
                }
                System.out.println("works");
                return true;
            case R.id.peel_hint:
                game.getSecondHint();
                System.out.println("peel works");
                pb_attempt.setProgress(game.getAttempts(), true);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hints_menu, menu);
        return true;
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
