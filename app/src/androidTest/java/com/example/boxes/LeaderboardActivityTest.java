package com.example.boxes;

import android.view.View;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardActivityTest {

    public @Rule
    ActivityTestRule<LeaderboardActivity> activityTestRule = new ActivityTestRule(LeaderboardActivity.class);

    private LeaderboardActivity leaderboardActivity = null;

    @Before
    public void setUp() throws Exception {
        leaderboardActivity = activityTestRule.getActivity();
    }

    @Test @UiThreadTest
    public void checkIfGenerated() {
        View viewText = leaderboardActivity.findViewById(R.id.leaderboardText);
        View viewButton = leaderboardActivity.findViewById(R.id.leaderboardBtn);
        assertNotNull(viewText);
        assertNotNull(viewButton);
    }

    @After
    public void tearDown() throws Exception {
        leaderboardActivity = null;
    }
}