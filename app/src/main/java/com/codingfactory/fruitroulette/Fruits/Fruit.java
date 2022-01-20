package com.codingfactory.fruitroulette.Fruits;

public abstract class Fruit {

    boolean pealing;
    boolean seeds;
    int discovered = 0;
    String img;
    int id;

    public Fruit() {

    }

    public boolean isPealable() {
        return pealing;
    }

    public boolean hasSeeds() {
        return seeds;
    }

    public int isDiscovered() {
        return discovered;
    }

    public void setDiscovered(int discovered) {
        this.discovered = discovered;
    }

    public String getImg() { return this.img; }

    public int getId() {
        return this.id;
    }
}
