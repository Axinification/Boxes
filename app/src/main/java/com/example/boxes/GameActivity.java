package com.example.boxes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    private Button start, menu, restart, menuScore;
    private Button[] allButtons;
    private TextView counter, timer, scoreText, highScoreText;
    private int startingTime = 10000;
    private int points = 0;
    private ViewFlipper viewFlipper;
    long timeLeft;
    String personId, personName;
    UserHelper helper;
    FirebaseDatabase database;
    DatabaseReference reference;
    private static int[] colors;
    boolean isStarted = false;
    boolean flag = true;
    private CountDownTimer countDownTimer;
    private FusedLocationProviderClient fusedLocationClient;
    String country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set start/restart button event listener
        start = findViewById(R.id.start);
        start.setOnClickListener(v -> startHandler());

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(v -> startActivity(new Intent(GameActivity.this, MenuActivity.class)));

        viewFlipper = findViewById(R.id.viewFlipper);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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
        allButtons = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7,
                btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16,
                btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25};
        for (Button button : allButtons) {
            button.setOnClickListener(v -> clickHandler(button));
        }

        //Game over screen
        restart = findViewById((R.id.restart));
        restart.setOnClickListener(v -> restartOnClick());

        menuScore = findViewById(R.id.menuScore);
        menuScore.setOnClickListener(v -> startActivity(new Intent(GameActivity.this, MenuActivity.class)));

        scoreText = findViewById(R.id.score);
        highScoreText = findViewById(R.id.highScore);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personId = acct.getId();
            personName = acct.getDisplayName();
        }

        helper = new UserHelper();
    }

    //Start handler function
    public void startHandler() {
        start.setClickable(false);
        start.getBackground().setAlpha(128);
        timer.setText(R.string.timer);
        addBoxes();
        setTimer(startingTime);
        isStarted = !isStarted;
    }

    //Restart handler function
    public void restartHandler() {
        points = 0;

        countDownTimer.cancel();
        counter.setText(String.valueOf(points));
        isStarted = false;
        flag = true;
        resetTimer();
        for (int i = allButtons.length - 1; i >= 0; i--) {
            allButtons[i].setVisibility(View.INVISIBLE);
            allButtons[i].getBackground().setAlpha(255);
        }
        flipView();
    }

    public void restartOnClick() {
        start.setClickable(true);
        start.getBackground().setAlpha(255);
        restartHandler();
    }

    public Button firstClick;
    public Button secondClick;
    public int firstColorCheck;
    public int secondColorCheck;

    //Box click handler function
    //Boxes are matched based on color
    public void clickHandler(View view) {
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
                if (timeLeft < 0) {
                    timeUp();
                } else {
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
        Random color = new Random();
        int selectedColor = colors[color.nextInt(colors.length)];
        if (isStarted) {
            for (int i = 0; i < 3; i++) {
                selectedButton[i].setBackgroundColor(selectedColor);
                selectedButton[i].setTextColor(selectedColor);
                selectedButton[i].setVisibility(View.VISIBLE);
                selectedButton[i].setClickable(true);
            }
        } else {
            for (int i = 0; i < 2; i++) {
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

        if (randomButton1.getId() != randomButton2.getId() && randomButton2.getId() != randomButton3.getId()) {
            return new Button[]{randomButton1, randomButton2, randomButton3};
        } else {
            return selectButtons(allButtons);
        }
    }

    //Setting timer for the game
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

    //Reseting timer
    public void resetTimer() {
        countDownTimer.cancel();
        timer.setText(R.string.timer);
    }

    //Game over logic
    public void timeUp() {
        getData();
        saveData();
        restartHandler();
    }

    //Get data from firebase
    public void getData() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("usersScores").child(personId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CharSequence highScorePulled = dataSnapshot.child("highScore").getValue().toString();
                highScoreText.setText(highScorePulled);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                highScoreText.setText(0);
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        country = location.toString();
                    } else {
                        country = getApplicationContext().getResources().getConfiguration().locale.getDisplayCountry();
                    }
                }
            });
        } else {
//            askLocationPermission();
            country = getApplicationContext().getResources().getConfiguration().locale.getDisplayCountry();
        }
    }

    //Collect and save data
    public void saveData() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("usersScores");
        CharSequence score = counter.getText();




        scoreText.setText(score);
        String scoreString = score.toString();
        if(Integer.parseInt(scoreString)>Integer.parseInt(highScoreText.getText().toString())) {
            helper.setName(personName);
            helper.setCountry(country);
            helper.setHighScore(scoreString);
            reference.child(personId).setValue(helper);
        }
    }

    //Nested view flipper
    public void flipView() {
        viewFlipper.showNext();
    }

}

    