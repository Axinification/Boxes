package com.example.boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    public Button start;
    public Button leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        start = findViewById(R.id.startBtn);
        start.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, GameActivity.class)));

        leaderboard = findViewById(R.id.leaderboardBtn);
        leaderboard.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, LeaderboardActivity.class)));

    }
}