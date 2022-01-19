package com.codingfactory.fruitroulette.logic;

import com.codingfactory.fruitroulette.Fruits.*;

import java.util.ArrayList;
import java.util.Random;

public class RandomFruits {


    public static ArrayList<Fruit> randomFruit() {
        ArrayList<Fruit> fruitArray = new ArrayList<>();
        fruitArray.add(new Banana());
        fruitArray.add(new Plum());
        fruitArray.add(new Raspberry());
        fruitArray.add(new Orange());
        fruitArray.add(new Grape());
        fruitArray.add(new Lemon());
        fruitArray.add(new Kiwi());
        fruitArray.add(new Strawberry());


        ArrayList<Fruit> fruitGameArray= new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int max = fruitArray.size();

            Random rand = new Random();
            int i_randomFruit = rand.nextInt(max);

            fruitGameArray.add(fruitArray.get(i_randomFruit));

            fruitArray.remove(i_randomFruit);

        }
        return fruitGameArray;
    }
}
