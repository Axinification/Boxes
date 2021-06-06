package com.example.boxes;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserHelperTest {

    @Test
    public void checkNameGetter() {
        String name = "testName";
        UserHelper userHelper = new UserHelper();
        userHelper.setName(name);
        assertEquals(name, userHelper.getName());
    }

    @Test
    public void checkNameSetter() {
        String name = "testName";
        UserHelper userHelper = new UserHelper();
        userHelper.setName(name);
        assertEquals(userHelper.getName(), name);
    }

    @Test
    public void checkCountryGetter() {
        String country = "testCountry";
        UserHelper userHelper = new UserHelper();
        userHelper.setCountry(country);
        assertEquals(country, userHelper.getCountry());
    }

    @Test
    public void checkCountrySetter() {
        String country = "testName";
        UserHelper userHelper = new UserHelper();
        userHelper.setName(country);
        assertEquals(userHelper.getName(), country);
    }

    @Test
    public void checkHighscoreGetter() {
        String highScore = "100";
        UserHelper userHelper = new UserHelper();
        userHelper.setHighScore(highScore);
        assertEquals(highScore, userHelper.getHighScore());
    }

    @Test
    public void checkHighscoreSetter() {
        String highScore = "100";
        UserHelper userHelper = new UserHelper();
        userHelper.setName(highScore);
        assertEquals(userHelper.getName(), highScore);
    }
}