package com.codingfactory.fruitroulette.fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Fruit {
    STRAWBERRY ("ic_strawberry",false, false, 1),
    BANANA   ("ic_banana",false, true, 2),
    RASPBERRY ("ic_raspberry",false, false, 3),
    KIWI ("ic_kiwi",false, true, 4),
    ORANGE ("ic_orange",true, true, 5),
    PLUM ("ic_plum",true, false, 6),
    GRAPES ("ic_grapes",true, false, 7),
    LEMON ("ic_lemon",true, true, 8);

    private final String img;
    private final boolean seeds;
    private final boolean peeling;
    private final int id;

    Fruit(String img, boolean seeds, boolean peeling, int id) {
        this.img = img;
        this.seeds = seeds;
        this.peeling = peeling;
        this.id = id;
    }

    public boolean hasSeeds() {
        return this.seeds;
    }

    public boolean needsPeeling() {
        return this.peeling;
    }

    public static List<String> getImgList() {
        List<String> imgList = new ArrayList<>();
        for (Fruit i : Fruit.values()) {
            imgList.add(i.img);
        }
        return imgList;
    }

    public static String getFruitName(int id) {
        String name = "";
        for (Fruit i : Fruit.values()) {
            if (i.id == id) {
                name = i.name();
                break;
            }
        }
        return name.toLowerCase();
    }
}