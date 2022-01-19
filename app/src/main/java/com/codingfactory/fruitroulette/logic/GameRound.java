package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.Fruits.Fruit;

import java.util.ArrayList;

public class GameRound {


    private final ArrayList<Fruit> pickedArray;

    public GameRound(ArrayList<Fruit> pickedArray) {
        this.pickedArray = pickedArray;
    }

    public ArrayList<Fruit> getPickedArray() {
        return pickedArray;
    }

    public void GamePlay(GameIteration CurrentGameIteration) {
        ConsoleDisplays.FruitDisplay(CurrentGameIteration.getRow());
        for (Fruit pikedFruit : pickedArray) {
            if (pikedFruit.getClass().getSimpleName().equals(CurrentGameIteration.getRow().get(pickedArray.indexOf(pikedFruit)).getClass().getSimpleName())) {
                System.out.println("vous avez découvert le fruit : " + pikedFruit.getClass().getSimpleName()
                        + " en position " + pickedArray.indexOf(pikedFruit));
                CurrentGameIteration.getRow().get(pickedArray.indexOf(pikedFruit)).setDiscovered(true);
                CurrentGameIteration.incFruitDiscovered();
            } else {
                System.out.println(pikedFruit.getClass().getSimpleName() + " ne correspond pas au fruit en case " + (pickedArray.indexOf(pikedFruit) + 1));
            }
        }
    }
}
