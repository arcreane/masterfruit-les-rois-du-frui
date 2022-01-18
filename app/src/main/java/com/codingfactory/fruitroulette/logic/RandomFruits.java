package com.codingfactory.fruitroulette.logic;

import java.util.ArrayList;
import java.util.Random;

public class RandomFruits {


    public ArrayList randomFruit () {
        ArrayList<String> fruitArray = new ArrayList<>();
        fruitArray.add("STRAWBERRY");
        fruitArray.add("BANANA");
        fruitArray.add("RASPERRY");
        fruitArray.add("KIWI");
        fruitArray.add("ORANGE");
        fruitArray.add( "PLUM");
        fruitArray.add("GRAPE");
        fruitArray.add("LEMON");

        ArrayList<String> fruitGameArray= new ArrayList<String>();

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
