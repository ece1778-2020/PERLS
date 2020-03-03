package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmotionSelector extends AppCompatActivity {
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    String ts, uid;
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
        uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);

        mAnnoyed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, uid);
            }
        });

        mIrritated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, uid);
            }
        });

        mFrustrated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, uid);
            }
        });

        mAggrevated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, uid);
            }
        });

        mIrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, uid);
            }
        });

        mEnraged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExercise("annoyed" , ts, uid);
            }
        });

    }

    public void startExercise(String type, String ts, String uid){
        /*
        * Change the mainActivity to the exercise activity when that's implemented
        * */
        Intent  intent = new Intent(EmotionSelector.this, ExerciseActivity.class);
        intent.putExtra(EXERCISE_MESSAGE_ID, uid);
        intent.putExtra(TIMESTAMP_ID, ts);
        intent.putExtra(EMOTION_ID, type);
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
