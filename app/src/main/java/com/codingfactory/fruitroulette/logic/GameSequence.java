package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.fruit.Fruity;
import com.codingfactory.fruitroulette.ui.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSequence {

    private final List<Fruity> possibleFruit;
    private int attempts, fruitDiscovered, cumulatedScore, round;
    private boolean firstHintGiven;
    private List<Integer> hiddenFruit;
    private RecyclerAdapter adapter;

    public GameSequence() {
        this.attempts = 10;
        this.fruitDiscovered = 0;
        this.cumulatedScore = 0;
        this.round = 0;
        this.possibleFruit = new ArrayList<>();
        possibleFruit.add(Fruity.STRAWBERRY);
        possibleFruit.add(Fruity.BANANA);
        possibleFruit.add(Fruity.RASPBERRY);
        possibleFruit.add(Fruity.KIWI);
        possibleFruit.add(Fruity.ORANGE);
        possibleFruit.add(Fruity.PLUM);
        possibleFruit.add(Fruity.GRAPES);
        possibleFruit.add(Fruity.LEMON);
        this.hiddenFruit = fruitGenerator();
    }

    public List<Fruity> getPossibleFruit() {
        return this.possibleFruit;
    }

    private List<Integer> fruitGenerator() {
        List<Integer> hiddenFruit = new ArrayList<>();
        Random random = new Random();
        while (hiddenFruit.size() < 4) {
            int fruit = random.nextInt(8);
            if (!hiddenFruit.contains(fruit)) hiddenFruit.add(fruit);
        }
        System.out.println("hidden 0: " + hiddenFruit.get(0));
        System.out.println("hidden 1: " + hiddenFruit.get(1));
        System.out.println("hidden 2: " + hiddenFruit.get(2));
        System.out.println("hidden 3: " + hiddenFruit.get(3));
        return hiddenFruit;
    }

    public void getHint(int whichHint) {
        String hintImg;
        if (whichHint == 1) hintImg = "ic_seeds";
        else hintImg = "ic_peel";
        String[] seedImg = new String[4];
        for (int i = 0; i < 4; i++) {
            if (whichHint == 1 && this.getPossibleFruit().get(this.hiddenFruit.get(i)).hasSeeds()) {
                seedImg[i] = hintImg;
            } else if (whichHint == 2 && this.getPossibleFruit().get(this.hiddenFruit.get(i)).needsPeeling()) {
                seedImg[i] = hintImg;
            } else {
                seedImg[i] = "ic_blank_hint";
            }
        }
        if (!firstHintGiven) {
            firstHintGiven = true;
            attempts -= 2;
        } else attempts -= 3;
        adapter.addPositions(0, 0);
        adapter.newLine(seedImg);
    }

    public boolean canIGetAHint() {
        if (firstHintGiven) return this.attempts - 3 > 0;
        else return this.attempts - 2 > 0;
    }

    public boolean makeAGuess(int[] guessed) {
        // Each of the four fruits are checked against the generated hidden fruit list.
        this.attempts--;
        int rightPosition = 0;
        int wrongPosition = 0;
        this.fruitDiscovered = 0;
        String[] recyclerLine = new String[4];
        for (int i = 0; i < 4; i++) {
            if (guessed[i] == this.hiddenFruit.get(i)) {
                rightPosition++;
                fruitDiscovered++;
            } else if (this.hiddenFruit.contains(guessed[i])) {
                wrongPosition++;
            }
            recyclerLine[i] = getPossibleFruit().get(guessed[i]).getImg();
        }
        adapter.newLine(recyclerLine);
        adapter.addPositions(rightPosition, wrongPosition);
        if (fruitDiscovered == 4) {
            cumulatedScore += attempts;
            round++;
        }
        return roundOver();
    }

    public void newRound() {
        attempts = 10;
        adapter.clear();
        hiddenFruit = fruitGenerator();
    }

    public void setAdapter(RecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public boolean isItAWin() {
        return fruitDiscovered == 4;
    }

    public boolean roundOver() {
        return (attempts == 0 || isItAWin());
    }

    public void reset() {
        newRound();
        cumulatedScore = 0;
        round = 0;
    }

    public int getRound() {
        return this.round;
    }

    public int getCumulatedScore() {
        return this.cumulatedScore;
    }
}
