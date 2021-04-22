package com.example.boxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


public class MenuActivity extends AppCompatActivity {

    public Button start, leaderboard;
    public TextView username;
    public ImageView userImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        start = findViewById(R.id.startBtn);
        start.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, GameActivity.class)));

        leaderboard = findViewById(R.id.leaderboardBtn);
        leaderboard.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, LeaderboardActivity.class)));

        username = findViewById(R.id.username);
        userImg = findViewById(R.id.userImg);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            username.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(userImg);
        } else {
            username.setText("Player");
        }
    }

}