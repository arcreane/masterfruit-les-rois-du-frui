package com.codingfactory.fruitroulette.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.logic.GameSequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewGame extends AppCompatActivity {

    private Spinner firstChoice, secondChoice, thirdChoice, fourthChoice;
    private Spinner[] choices;
    private GameSequence game;
    private ProgressBar pb_attempt;
    private ImageView get_hint;
    private Dialog dialog;
    private RecyclerAdapter adapter;
    private RecyclerView guessView;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.gameplay);
        game = new GameSequence();
        dialog = new Dialog(this);

        firstChoice = findViewById(R.id.first);
        secondChoice = findViewById(R.id.second);
        thirdChoice = findViewById(R.id.third);
        fourthChoice = findViewById(R.id.fourth);
        pb_attempt = findViewById(R.id.pb_attempt);
        get_hint = findViewById(R.id.get_hint);

        List<String> fruitImgs = new ArrayList<>();
        game.getPossibleFruit().stream().sorted().forEach(e -> fruitImgs.add(e.getImg()));

        SpinnerAdapter fruitAdapter = new SpinnerAdapter(getApplicationContext(), fruitImgs);
        fruitAdapter.setDropDownViewResource(R.layout.dropdown_fruit);

        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};
        Arrays.stream(choices).sequential().forEach(e -> e.setAdapter(fruitAdapter));

        //config of the progress bar

        pb_attempt.setMax(10);
        pb_attempt.setMin(0);
        pb_attempt.setProgress(10);
        score = findViewById(R.id.score_count);

        //Validation of the spinners on click guess button

        Button guessButton = findViewById(R.id.b_Guess);
        guessButton.setOnClickListener(view -> {
            if (emptyFields()) {
                Toast.makeText(getApplicationContext(), "Uh oh, some fruit is missing!", Toast.LENGTH_SHORT).show();
            } else if (distinctChoices()) {
                int firstFruit = firstChoice.getSelectedItemPosition()-1;
                int secondFruit = secondChoice.getSelectedItemPosition()-1;
                int thirdFruit = thirdChoice.getSelectedItemPosition()-1;
                int fourthFruit = fourthChoice.getSelectedItemPosition()-1;
                int intArray[] = {firstFruit, secondFruit, thirdFruit, fourthFruit};
                if (game.makeAGuess(intArray)) {
                    openEndGameDialog(R.layout.end_dialog);
                    adapter.clear();
                    System.out.println(game.getCumulatedScore());
                    score.setText(String.valueOf(game.getCumulatedScore()));
                    Arrays.stream(choices).sequential().forEach(e -> e.setSelection(0));
                }
                pb_attempt.setProgress(game.getAttempts(), true);
                guessView.smoothScrollToPosition(adapter.getItemCount());
            } else {
                Toast.makeText(getApplicationContext(), "Uh oh, no two fruits can be the same!",Toast.LENGTH_SHORT).show();
            }
        });

        guessView = findViewById(R.id.guessView);
        adapter = new RecyclerAdapter(this);
        guessView.setAdapter(adapter);
        guessView.setLayoutManager(new LinearLayoutManager(this));
        game.setAdapter(adapter);
        choices = new Spinner[]{firstChoice, secondChoice, thirdChoice, fourthChoice};

        get_hint.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(getApplicationContext(), get_hint);
            popupMenu.getMenuInflater().inflate(R.menu.hints_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (game.canIGetAHint()) {
                    switch (menuItem.getItemId()) {
                        case R.id.seed_hint:
                            game.getHint(1);
                            pb_attempt.setProgress(game.getAttempts(), true);
                            return true;
                        case R.id.peel_hint:
                            game.getHint(2);
                            pb_attempt.setProgress(game.getAttempts(), true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Uh oh, not enough points!",Toast.LENGTH_SHORT).show();
                }
                return false;
            });
            popupMenu.show();
        });
    }

    private void openEndGameDialog(int resourceId) {
        dialog.setContentView(resourceId);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        if (resourceId == R.layout.end_dialog) {
            TextView score = dialog.findViewById(R.id.win_or_lose);
            score.setText("Score: " + game.getCumulatedScore());
            TextView rounds = dialog.findViewById(R.id.rounds);
            rounds.setText("Rounds: " + game.getRound());
            if (!game.isItAWin()) {
                dialog.findViewById(R.id.new_round_button).setVisibility(View.GONE);
                ImageView trophy = dialog.findViewById(R.id.trophy_img);
                trophy.setColorFilter(Color.GRAY);
            }
        }
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

    public void newRound (View view) {
        pb_attempt.setProgress(10);
        game.newRound();
        dialog.dismiss();
    };

    public void restart(View view) {
        pb_attempt.setProgress(10);
        game.reset();
        score.setText("0");
        dialog.dismiss();
        adapter.clear();
    }

    public void quit(View view) {
        if (game.getCumulatedScore() > 0) {
            openEndGameDialog(R.layout.new_score_dialog);
        } else {
            finish();
        }
    }

    public void backToMainMenu(View view) {
        finish();
    }

    public void addScore(View view) {
        EditText playerName = dialog.findViewById(R.id.playerName);
        if (!playerName.getText().equals("")) {
            Intent scoresIntent = new Intent(NewGame.this, HighScores.class);
            scoresIntent.putExtra("playerName", playerName.getText().toString());
            scoresIntent.putExtra("finalScore", game.getCumulatedScore());
            scoresIntent.putExtra("finalRound", game.getRound());
            startActivity(scoresIntent);
            dialog.dismiss();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        openEndGameDialog(R.layout.end_dialog);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }
}
