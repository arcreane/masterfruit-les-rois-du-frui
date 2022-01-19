package com.codingfactory.fruitroulette.fruit;

import java.util.ArrayList;
import java.util.List;

public enum Fruity {
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

    Fruity(String img, boolean seeds, boolean peeling, int id) {
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
        for (Fruity i : Fruity.values()) {
            imgList.add(i.img);
        }
        return imgList;
    }

    public static String getFruitName(int id) {
        String name = "";
        for (Fruity i : Fruity.values()) {
            if (i.id == id) {
                name = i.name();
                break;
            }
        }
        return name.toLowerCase();
    }

    public static String getFruitImg(int id) {
        for (Fruity i : Fruity.values()) {
            if (i.id == id) return i.img;
        }
        return null;
    }
}