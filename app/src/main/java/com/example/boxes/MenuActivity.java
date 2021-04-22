package com.example.boxes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class MenuActivity extends AppCompatActivity {

    public Button start, leaderboard, signOut, exit;
    public TextView username;
    public ImageView userImg;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        start = findViewById(R.id.startBtn);
        start.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, GameActivity.class)));

        leaderboard = findViewById(R.id.leaderboardBtn);
        leaderboard.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, LeaderboardActivity.class)));

        username = findViewById(R.id.username);
        userImg = findViewById(R.id.userImg);

        exit = findViewById(R.id.exitBtn);
        exit.setOnClickListener(v -> finishAffinity());
        signOut = findViewById(R.id.signOutBtn);
        signOut.setOnClickListener(v -> signOut());

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

    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(MenuActivity.this, "Signed out successfully!", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(MenuActivity.this, AuthActivity.class));
//                    }
//                });
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MenuActivity.this, AuthActivity.class));
    }

}