package com.example.boxes;

import android.view.View;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuActivityTest {
    public @Rule
    ActivityTestRule<MenuActivity> activityTestRule = new ActivityTestRule(MenuActivity.class);

    private MenuActivity menuActivity = null;

//    @Test
//    public void onCreate() {
//
//    }

    @Before
    public void setUp() throws Exception {
        menuActivity = activityTestRule.getActivity();
    }


    @Test @UiThreadTest
    public void checkIfNameIsPlayerOnNullAcct() {
        String expected = "Player";
        menuActivity.setName(null);
        assertEquals(menuActivity.username.getText(), expected);
    }

    @After
    public void tearDown() throws Exception {
        menuActivity = null;
    }
}