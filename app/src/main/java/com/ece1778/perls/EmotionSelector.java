package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EmotionSelector extends AppCompatActivity {
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String INTENSITY_ID="intensityId";

    private int intensity;
    private String ts, uid, session_id;
    private Button  mAnnoyed,
                    mIrritated,
                    mFrustrated,
                    mAggrevated,
                    mIrate,
                    mEnraged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_selector);

        setUI();

        Intent intent = getIntent();
        ts = intent.getStringExtra(TIMESTAMP_ID);
        session_id = intent.getStringExtra(SESSION_ID);
        intensity = intent.getIntExtra(INTENSITY_ID, 0);
        //Log.d("checking session id: ", session_id);

        mAnnoyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, session_id);
            }
        });

        mIrritated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("irritated" , ts, session_id);
            }
        });

        mFrustrated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("frustrated" , ts, session_id);
            }
        });

        mAggrevated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("aggravated" , ts, session_id);
            }
        });

        mIrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("irate" , ts, session_id);
            }
        });

        mEnraged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("enraged" , ts, session_id);
            }
        });

    }

    public void startExercise(String type, String ts, String session_id){
        Intent  intent = new Intent(EmotionSelector.this, SelectorActivity.class);
        intent.putExtra(TIMESTAMP_ID, ts);
        intent.putExtra(EMOTION_ID, type);
        intent.putExtra(SESSION_ID, session_id);
        intent.putExtra(INTENSITY_ID, intensity);
        startActivity(intent);
    }

    public void setUI(){
        mAnnoyed = findViewById(R.id.emotion_annoyed);
        mIrritated = findViewById(R.id.emotion_irritated);
        mFrustrated = findViewById(R.id.emotion_frustrated);
        mAggrevated = findViewById(R.id.emotion_aggrevated);
        mIrate = findViewById(R.id.emotion_irate);
        mEnraged = findViewById(R.id.emotion_enraged);
    }
}
