package com.example.boxes;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameActivityTest {

    public @Rule
    ActivityTestRule<GameActivity> activityTestRule = new ActivityTestRule(GameActivity.class);

    private GameActivity gameActivity = null;

    //Set buttons
    Button btn1 = gameActivity.findViewById(R.id.btn1);
    Button btn2 = gameActivity.findViewById(R.id.btn2);
    Button btn3 = gameActivity.findViewById(R.id.btn3);
    Button btn4 = gameActivity.findViewById(R.id.btn4);
    Button btn5 = gameActivity.findViewById(R.id.btn5);
    Button btn6 = gameActivity.findViewById(R.id.btn6);
    Button btn7 = gameActivity.findViewById(R.id.btn7);
    Button btn8 = gameActivity.findViewById(R.id.btn8);
    Button btn9 = gameActivity.findViewById(R.id.btn9);
    Button btn10 = gameActivity.findViewById(R.id.btn10);
    Button btn11 = gameActivity.findViewById(R.id.btn11);
    Button btn12 = gameActivity.findViewById(R.id.btn12);
    Button btn13 = gameActivity.findViewById(R.id.btn13);
    Button btn14 = gameActivity.findViewById(R.id.btn14);
    Button btn15 = gameActivity.findViewById(R.id.btn15);
    Button btn16 = gameActivity.findViewById(R.id.btn16);
    Button btn17 = gameActivity.findViewById(R.id.btn17);
    Button btn18 = gameActivity.findViewById(R.id.btn18);
    Button btn19 = gameActivity.findViewById(R.id.btn19);
    Button btn20 = gameActivity.findViewById(R.id.btn20);
    Button btn21 = gameActivity.findViewById(R.id.btn21);
    Button btn22 = gameActivity.findViewById(R.id.btn22);
    Button btn23 = gameActivity.findViewById(R.id.btn23);
    Button btn24 = gameActivity.findViewById(R.id.btn24);
    Button btn25 = gameActivity.findViewById(R.id.btn25);

    @Before
    public void setUp() throws Exception {
        gameActivity = activityTestRule.getActivity();
    }

    @Test
    public void onCreate() {
    }

    @Test @UiThreadTest
    public void testStartHandler() {
        Button start = gameActivity.findViewById(R.id.start);
        TextView timer = gameActivity.findViewById(R.id.timer);
        gameActivity.isStarted = false;

        gameActivity.startHandler();

        assertFalse(start.isClickable());
        assertEquals(start.getBackground().getAlpha(), 128);
        assertEquals(timer.getText(),"00:00");
        assertTrue(gameActivity.isStarted);

    }

    @Test
    public void testRestartHandler() {
        int[] allButtonsAlpha = new int[]{btn1.getBackground().getAlpha(), btn2.getBackground().getAlpha(), btn3.getBackground().getAlpha(),
                btn4.getBackground().getAlpha(), btn5.getBackground().getAlpha(), btn6.getBackground().getAlpha(), btn7.getBackground().getAlpha(),
                btn8.getBackground().getAlpha(), btn9.getBackground().getAlpha(), btn10.getBackground().getAlpha(), btn11.getBackground().getAlpha(),
                btn12.getBackground().getAlpha(), btn13.getBackground().getAlpha(), btn14.getBackground().getAlpha(), btn15.getBackground().getAlpha(),
                btn16.getBackground().getAlpha(), btn17.getBackground().getAlpha(), btn18.getBackground().getAlpha(), btn19.getBackground().getAlpha(),
                btn20.getBackground().getAlpha(), btn21.getBackground().getAlpha(), btn22.getBackground().getAlpha(), btn23.getBackground().getAlpha(),
                btn24.getBackground().getAlpha(), btn25.getBackground().getAlpha()};

        int[] expectedAlphaArray = new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255, 255,
                255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255};

        CharSequence points = "0";
        TextView counter = gameActivity.findViewById(R.id.points);
        TextView timer = gameActivity.findViewById(R.id.timer);
        gameActivity.flag = true;
        gameActivity.setTimer(0);

        gameActivity.restartHandler();

        assertEquals(counter.getText(), points);
        assertFalse(gameActivity.isStarted);
        assertEquals(timer.getText(), "00:00");
        assertFalse(gameActivity.flag);
        assertEquals(allButtonsAlpha, expectedAlphaArray);
    }

    @Test
    public void restartOnClick() {
    }

    @Test
    public void clickHandler() {
    }

    @Test
    public void addBoxes() {
    }

    @Test
    public void setTimer() {
    }

    @Test
    public void resetTimer() {
    }

    @Test
    public void timeUp() {
    }

    @Test
    public void getData() {
    }

    @Test
    public void saveData() {
    }

    @Test
    public void flipView() {
    }

    @After
    public void tearDown() throws Exception {
        gameActivity = null;
    }
}