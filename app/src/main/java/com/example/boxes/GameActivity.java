package com.example.boxes;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    public Button start;
    public Button restart;
    public Button menu;
    public Button[] allButtons;
    public TextView counter;
    public TextView timer;
    public View boxContainer;
    public int startingTime = 10000;
    long timeLeft;


    public int points = 0;


    public static int[] colors;
    boolean isStarted = false;
    boolean flag = true;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set start/restart button event listener
        start = findViewById(R.id.start);
        start.setOnClickListener(v -> startHandler());

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(v -> startActivity(new Intent(GameActivity.this, MenuActivity.class)));

        restart = findViewById((R.id.restart));
        restart.setOnClickListener(v -> restartHandler());
        restart.setClickable(false);
        restart.getBackground().setAlpha(128);

        boxContainer = findViewById(R.id.boxBoard);


        //Set color list
        TypedArray ta = getResources().obtainTypedArray(R.array.buttonColors);
        colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();

        //Set timer
        timer = findViewById(R.id.timer);

        //Set counter
        counter = findViewById(R.id.points);

        //Set buttons
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn10 = findViewById(R.id.btn10);
        Button btn11 = findViewById(R.id.btn11);
        Button btn12 = findViewById(R.id.btn12);
        Button btn13 = findViewById(R.id.btn13);
        Button btn14 = findViewById(R.id.btn14);
        Button btn15 = findViewById(R.id.btn15);
        Button btn16 = findViewById(R.id.btn16);
        Button btn17 = findViewById(R.id.btn17);
        Button btn18 = findViewById(R.id.btn18);
        Button btn19 = findViewById(R.id.btn19);
        Button btn20 = findViewById(R.id.btn20);
        Button btn21 = findViewById(R.id.btn21);
        Button btn22 = findViewById(R.id.btn22);
        Button btn23 = findViewById(R.id.btn23);
        Button btn24 = findViewById(R.id.btn24);
        Button btn25 = findViewById(R.id.btn25);

        //Declare buttons list and click function
        allButtons = new Button[] {btn1, btn2, btn3, btn4, btn5, btn6, btn7,
                btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16,
                btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25};
        for (Button button : allButtons) {
            button.setOnClickListener(v -> clickHandler(button));
        }
    }
    //TODO: Work on GUI
    //TODO: Setup Firebase Google authentication and send points with current location to database for ranking
    //Start handler function
    public void startHandler() {
        boxContainer.setVisibility(View.VISIBLE);
        start.setClickable(false);
        start.getBackground().setAlpha(128);
        restart.setClickable(true);
        restart.getBackground().setAlpha(255);
        timer.setText(R.string.timer);
        addBoxes();
        setTimer(startingTime);
        isStarted=!isStarted;
    }

    //Restart handler function
    public void restartHandler() {
        points = 0;

        countDownTimer.cancel();
        counter.setText(String.valueOf(points));
        isStarted = false;
        flag = true;
        start.setClickable(true);
        restart.setClickable(false);
        restart.getBackground().setAlpha(128);
        start.getBackground().setAlpha(255);
        resetTimer();
        for (int i = allButtons.length - 1; i >= 0; i--) {
            allButtons[i].setVisibility(View.INVISIBLE);
            allButtons[i].getBackground().setAlpha(255);
        }
        boxContainer.setVisibility(View.VISIBLE);
    }

    public Button firstClick;
    public Button secondClick;
    public int firstColorCheck;
    public int secondColorCheck;

    //Box click handler function
    //Boxes are matched based on color
    public void clickHandler(View view){
        if (flag) {
            firstClick = (Button) view;
            firstColorCheck = firstClick.getCurrentTextColor();
            view.setClickable(false);
            view.getBackground().setAlpha(128);
        } else {
            secondClick = (Button) view;
            secondColorCheck = secondClick.getCurrentTextColor();
            if (firstColorCheck == secondColorCheck) {
                //If matched
                //Hide and disable clicked buttons
                view.setClickable(false);
                firstClick.getBackground().setAlpha(255);
                firstClick.setVisibility(View.INVISIBLE);
                secondClick.setVisibility(View.INVISIBLE);
                //Increment and update points
                points++;
                counter.setText(String.valueOf(points));
                //Add one second and update timer
                timeLeft += 1000;
                countDownTimer.cancel();
                setTimer(timeLeft);
                //Add 3 new boxes
                addBoxes();
            } else {
                firstClick.setClickable(true);
                firstClick.getBackground().setAlpha(255);
                secondClick.setClickable(true);
                secondClick.getBackground().setAlpha(255);
                counter.setText(String.valueOf(points));
                timeLeft -= 5000;
                String str = timeLeft+"";
                Log.i("Time Left: ",str);
                if (timeLeft < 0) {
                    timeUp();
                }
                else {
                    countDownTimer.cancel();
                    setTimer(timeLeft);
                }
            }
        }
        flag = !flag;
    }

    //Add pair function
    public void addBoxes() {
        Button[] selectedButton = selectButtons(allButtons);
        Random color =  new Random();
        int selectedColor = colors[color.nextInt(colors.length)];
        if (isStarted) {
            for (int i=0;i<3;i++) {
                selectedButton[i].setBackgroundColor(selectedColor);
                selectedButton[i].setTextColor(selectedColor);
                selectedButton[i].setVisibility(View.VISIBLE);
                selectedButton[i].setClickable(true);
            }
        } else {
            for (int i=0;i<2;i++) {
                selectedButton[i].setBackgroundColor(selectedColor);
                selectedButton[i].setTextColor(selectedColor);
                selectedButton[i].setVisibility(View.VISIBLE);
                selectedButton[i].setClickable(true);
            }
        }
    }

    //Getting random buttons
    private static Button[] selectButtons(Button[] allButtons) {
        Random button = new Random();
        Button randomButton1 = allButtons[button.nextInt(allButtons.length)];
        Button randomButton2 = allButtons[button.nextInt(allButtons.length)];
        Button randomButton3 = allButtons[button.nextInt(allButtons.length)];

        if(randomButton1.getId() != randomButton2.getId() && randomButton2.getId() != randomButton3.getId()) {
            return new Button[]{randomButton1, randomButton2, randomButton3};
        } else {
            return selectButtons(allButtons);
        }
    }

    public void setTimer(long currentTime) {
        countDownTimer = new CountDownTimer(currentTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int min = (int) millisUntilFinished / 1000 / 60;
                int sec = (int) millisUntilFinished / 1000 % 60;
                timeLeft = millisUntilFinished;
                if (timeLeft < 0) timeUp();

                String time = String.format(Locale.getDefault(), "%02d:%02d", min, sec);

                timer.setText(time);
            }

            @Override
            public void onFinish() {
                timeUp();
            }
        }.start();
    }

    public void resetTimer() {
        countDownTimer.cancel();
        timer.setText(R.string.timer);
    }

    public void timeUp() {
        int score = Integer.parseInt(counter.getText().toString());
        restartHandler();
        boxContainer.setVisibility(View.INVISIBLE);
        Log.i("Score: ", ""+score);
    }

}

    