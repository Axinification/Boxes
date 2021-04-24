package com.example.boxes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private UserHelper user;
    private ArrayList<UserHelper> userList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("usersScores");

        userList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapter(this, userList);

        recyclerView.setAdapter(adapter);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        user = ds.getValue(UserHelper.class);
                        userList.add(user);
                    }
                    //Sorting
                    Collections.sort(userList, (Comparator<UserHelper>) (o1, o2) -> o2.getHighScore().compareTo(o1.getHighScore()));

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}