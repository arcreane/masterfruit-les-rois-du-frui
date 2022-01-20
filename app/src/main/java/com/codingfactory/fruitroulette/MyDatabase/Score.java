package com.codingfactory.fruitroulette.MyDatabase;

import java.io.Serializable;

public class Score implements Serializable {
    public Score(String name, String score) {
        this.name = name;
        this.score = score;
    }

    private String name;
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
