package com.codingfactory.fruitroulette.logic;

import java.util.ArrayList;

public class Game {

    private ArrayList<GameIteration> GameIterations = new ArrayList<>();
    private int totalScore;

    public Game() {
    }

    public void launchGame(){
        for (int i =0; i<3; i++){
            GameIteration curGameIteration = new GameIteration();
            curGameIteration.GameLoop();
            GameIterations.add(curGameIteration);
            this.totalScore += curGameIteration.getIterationScore();
            System.out.println(totalScore);
        }

    }
}
