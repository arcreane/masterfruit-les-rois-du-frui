package com.codingfactory.fruitroulette.Fruits;

public abstract class Fruit {

    boolean pealing;
    boolean seeds;
    boolean discovered;
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

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public String getImg() { return this.img; }

    public int getId() {
        return this.id;
    }
}
