package com.example.boxes;

public class ScoreboardHelper {

    String name, country;
    CharSequence score;

    public ScoreboardHelper() {

    }

//    public ScoreboardHelper(String name, String country, CharSequence score) {
//        this.name = name;
//        this.country = country;
//        this.score = score;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CharSequence getScore() {
        return score;
    }

    public void setScore(CharSequence score) {
        this.score = score;
    }
}
