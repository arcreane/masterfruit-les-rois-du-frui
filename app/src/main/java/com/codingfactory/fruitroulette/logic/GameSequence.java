package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.fruit.Fruity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSequence {

    private List<Fruity> possibleFruit;
    private int attempts, fruitDiscovered,iterationScore;
    private List<Integer> hiddenFruit;
    private Boolean[] firstHint, secondHint;
    private Integer[] guesses;

    public GameSequence() {
        this.attempts = 10;
        this.fruitDiscovered = 0;
        this.iterationScore = 0;
        this.possibleFruit = setPossibleFruit();
        this.hiddenFruit = fruitGenerator();
        this.firstHint = new Boolean[4];
        this.secondHint = new Boolean[4];
        this.guesses = new Integer[4];
        System.out.println("GS hidden fruit: " + this.hiddenFruit);
    }

    public List<Fruity> setPossibleFruit() {
        List<Fruity> possibleFruit = new ArrayList<>();
        possibleFruit.add(Fruity.STRAWBERRY);
        possibleFruit.add(Fruity.BANANA);
        possibleFruit.add(Fruity.RASPBERRY);
        possibleFruit.add(Fruity.KIWI);
        possibleFruit.add(Fruity.ORANGE);
        possibleFruit.add(Fruity.PLUM);
        possibleFruit.add(Fruity.GRAPES);
        possibleFruit.add(Fruity.LEMON);
        return possibleFruit;
    }

    public List<Fruity> getPossibleFruit() {
        return this.possibleFruit;
    }

    private List<Integer> fruitGenerator() {
        List<Integer> hiddenFruit = new ArrayList<>();
        System.out.println(hiddenFruit.size());
        Random random = new Random();
        while (hiddenFruit.size() < 4) {
            int fruit = random.nextInt(8);
            if (!hiddenFruit.contains(fruit)) hiddenFruit.add(fruit);
        }
        System.out.println(hiddenFruit);
        return hiddenFruit;
    }

    public Boolean[] getFirstHint() {
        if (canIGetAHint(2)) {
            for (int i : this.hiddenFruit) {
                boolean hasSeeds = this.possibleFruit.get(this.hiddenFruit.get(i)).hasSeeds();
                this.firstHint[i] = hasSeeds;
            }
            attempts -= 2;
            return this.firstHint;
        } else return null;
    }

    public Boolean[] getSecondHint() {
        if (canIGetAHint(3)) {
            for (int i : this.hiddenFruit) {
                boolean needsPeeling = this.possibleFruit.get(this.hiddenFruit.get(i)).needsPeeling();
                this.secondHint[i] = needsPeeling;
            }
            attempts -= 3;
            return this.secondHint;
        } else return null;
    }

    public boolean canIGetAHint(int value) {
        return this.attempts - value >= 0;
    }

    public boolean makeAGuess(int[] guessed) {
        // Each of the four fruits are checked against the generated hidden fruit list.
        // Adding 0 means the fruit is not on the list.
        // Adding 1 means the user has guessed correctly (right fruit, right place).
        // Adding 2 means the fruit is on the list but in a different position.
        for (int i = 0; i < 4; i++) {
            if (guessed[i] == this.hiddenFruit.get(i)) {
                this.guesses[i] = 1;
                fruitDiscovered++;
            } else if (this.hiddenFruit.contains(guessed[i])) {
                this.guesses[i] = 2;
            } else this.guesses[i] = 0;
        }
        return didIWin();
    }

    public int didIGuess(int i) {
        return this.guesses[i];
    }

    private boolean didIWin() {
        return this.fruitDiscovered == 4;
    }
}
