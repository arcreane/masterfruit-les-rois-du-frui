
package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.Fruits.*;

import java.util.ArrayList;
import java.util.List;

public class GameIteration {

    private int attempts;
    private int fruitDiscovered = 0;
    private int iterationScore = 0;
    private ArrayList<Fruit> row;
    private List<Boolean> firstHint;
    private List<Boolean> secondHint;
    private ArrayList<GameRound> roundHistory = new ArrayList<>();

    public GameIteration() {
        this.attempts = 10;
        this.row = RandomFruits.randomFruit();
        this.firstHint = new ArrayList<>();
        this.secondHint = new ArrayList<>();
    }

    public ArrayList<Fruit> getRow() {
        return row;
    }

    public int getIterationScore() {
        return iterationScore;
    }

    public ArrayList<ArrayList<Fruit>> getPickedFruitsHistory() {
        ArrayList<ArrayList<Fruit>> pickedFruitsHistory = new ArrayList<>();
        for (GameRound prevRound : roundHistory){
            pickedFruitsHistory.add(prevRound.getPickedArray());
        }
        System.out.println(pickedFruitsHistory);
        return pickedFruitsHistory;
    }

    public int getFruitDiscovered() {
        return fruitDiscovered;
    }

    public void incFruitDiscovered() {
        this.fruitDiscovered++;
    }


    public int getAttempts() {
        return attempts;
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
                this.secondHint.add(i.isPealable());
            }
            attempts -= 3;
            return this.secondHint;
        } else return null;
    }

    public boolean canIGetAHint(int value) {
        return this.attempts - value >= 0;
    }

    public void GameLoop() {
        while (this.getAttempts() > 0 && this.getFruitDiscovered() < 5) {
            GameRound currentRound = new GameRound(RandomFruits.randomFruit());
            currentRound.GamePlay(this);
            roundHistory.add(currentRound);
            this.getPickedFruitsHistory();
        }
        this.iterationScore += this.attempts;
    }
}

