package com.codingfactory.fruitroulette.Fruits;

public class Fruit {
    boolean pealing;
    boolean seeds;
    boolean discovered;

    public boolean isPealable() {
        return pealing;
    }

    public boolean hasSeeds() {
        return seeds;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }
}
