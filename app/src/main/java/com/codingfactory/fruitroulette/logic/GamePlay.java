package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

public class GamePlay {

    private int attempts;
    private List<Fruit> row;
    private List<Boolean> firstHint;
    private List<Boolean> secondHint;
    private List<List<Fruit>> attemptsHistory;

    public GamePlay() {
        this.attempts = 10;
        this.row = new ArrayList<>();
        this.firstHint = new ArrayList<>();
        this.secondHint = new ArrayList<>();
    }

    public void reset() {
        this.attempts = 10;
        this.row.clear();
        this.firstHint.clear();
        this.firstHint.clear();
    }

    public List<Boolean> getFirstHint() {
        if (canIGetAHint(2)) {
            for (Fruit i : this.row) {
                this.firstHint.add(i.hasSeeds());
            }
            attempts -= 2;
            return this.firstHint;
        } else return null;
    }

    public List<Boolean> getSecondHint() {
        if (canIGetAHint(3)) {
            for (Fruit i : this.row) {
                this.secondHint.add(i.needsPeeling());
            }
            attempts -= 3;
            return this.secondHint;
        } else return null;
    }

    public boolean canIGetAHint(int value) {
        return this.attempts - value >= 0;
    }
}
