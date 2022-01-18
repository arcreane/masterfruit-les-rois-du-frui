package com.codingfactory.fruitroulette.fruit;

public enum Fruit {
    STRAWBERRY ("toBeAdded",false, false),
    BANANA   ("toBeAdded",false, true),
    RASPBERRY ("toBeAdded",false, false),
    KIWI ("toBeAdded",false, true),
    ORANGE ("toBeAdded",true, true),
    PLUM ("toBeAdded",true, false),
    GRAPES ("toBeAdded",true, false),
    LEMON ("toBeAdded",true, true);

    private final String picture;
    private final boolean seeds;
    private final boolean peeling;

    Fruit(String picture, boolean seeds, boolean peeling) {
        this.picture = picture;
        this.seeds = seeds;
        this.peeling = peeling;
    }

    public boolean hasSeeds() {
        return this.seeds;
    }

    public boolean needsPeeling() {
        return this.peeling;
    }

    @Override
    public String toString() {
        return this.picture;
    }
}