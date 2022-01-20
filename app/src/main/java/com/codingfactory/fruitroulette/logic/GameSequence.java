package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.fruit.Fruity;
import com.codingfactory.fruitroulette.ui.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSequence {

    private int imgType;
    private List<Fruity> possibleFruit;
    private int attempts, fruitDiscovered,iterationScore;
    private int rightPosition, wrongPosition;
    private List<Integer> hiddenFruit;
    private Boolean[] firstHint, secondHint;
    private Integer[] guesses;
    private RecyclerAdapter adapter;

    public GameSequence() {
        this.attempts = 10;
        this.fruitDiscovered = 0;
        this.iterationScore = 0;
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
        this.firstHint = new Boolean[4];
        this.secondHint = new Boolean[4];
        this.guesses = new Integer[4];
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
        return hiddenFruit;
    }

    public void getFirstHint() {
        String[] seedImg = new String[4];
        if (canIGetAHint(2)) {
            for (int i = 0; i < 4; i++) {
                if (this.getPossibleFruit().get(this.hiddenFruit.get(i)).hasSeeds()) {
//                this.firstHint[i] = hasSeeds;
                seedImg[i] = "ic_seeds";
                } else {
                    seedImg[i] = "ic_no_seeds";
                }
            }
            attempts -= 2;
            this.imgType = 1;
            adapter.newLine(seedImg);
        };
    }

    public Boolean[] getSecondHint() {
        if (canIGetAHint(3)) {
            for (int i : this.hiddenFruit) {
                boolean needsPeeling = this.possibleFruit.get(this.hiddenFruit.get(i)).needsPeeling();
                this.secondHint[i] = needsPeeling;
            }
            attempts -= 3;
            this.imgType = 2;
            return this.secondHint;
        } else return null;
    }

    public boolean canIGetAHint(int value) {
        return this.attempts - value >= 0;
    }

    public boolean makeAGuess(int[] guessed) {
        // Each of the four fruits are checked against the generated hidden fruit list.
        this.attempts--;
        this.rightPosition = 0;
        this.wrongPosition = 0;
        String[] recyclerLine = new String[4];
        for (int i = 0; i < 4; i++) {
            if (guessed[i] == this.hiddenFruit.get(i)) {
                this.rightPosition++;
            } else if (this.hiddenFruit.contains(guessed[i])) {
                this.wrongPosition++;
            }
            recyclerLine[i] = getPossibleFruit().get(guessed[i]).getImg();
        }
        adapter.newLine(recyclerLine);
        this.imgType = 0;
        return didIWin();
    }

    public int didIGuess(int i) {
        return this.guesses[i];
    }

    private boolean didIWin() {
        return this.fruitDiscovered == 4;
    }

    public int getRightPosition() {
        return rightPosition;
    }

    public int getWrongPosition() {
        return wrongPosition;
    }

    public int getImgType() {
        return this.imgType;
    }

    public void setAdapter(RecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    public int getAttempts() {
        return this.attempts;
    }
}
