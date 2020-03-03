package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReflectionActivity extends AppCompatActivity {

    private TextView mTextView;
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private String timestamp, uid, emotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);

        //get this from the emotion passed from emotion selector -> exercise activity
        /*Intent intent = getIntent();
        emotion = intent.getStringExtra(EMOTION_ID);
        uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        timestamp = intent.getStringExtra(TIMESTAMP_ID);*/
        mTextView = findViewById(R.id.textView);
        mTextView.setText("You were feeling (put emotion here) before the exercise, how do you feel now?");



    }

    public void goHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
