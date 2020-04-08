package com.ece1778.perls;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;

public class SelectorActivity extends AppCompatActivity {
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String INTENSITY_ID="intensityId";
    private static final String EXERCISE = "exercise_name";
    private TextView recommender;
    private String session_ts, session_id, exercise_uid, exercise_ts, emotion_id;
    private int intensity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        recommender = findViewById(R.id.recommender);
        //Log.d("checking session id: ", session_id);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        intensity = intent.getIntExtra(INTENSITY_ID, 0);
        Log.d("intensity", " " + intensity);
        switch (intensity){
            case 1:
                recommender.setText(R.string.normal);
                break;
            case 2:
                recommender.setText(R.string.extreme);
                break;
            default:
                recommender.setText(R.string.choice);
        }
    }

    public void startOppositeAction(View view) {
        UUID exer_uid = UUID.randomUUID();
        Long ts = System.currentTimeMillis();
        Intent intent = new Intent(this, LessonActivity.class);
        intent.putExtra(SESSION_ID, session_id);
        intent.putExtra(TIMESTAMP_ID, session_ts);
        intent.putExtra(EMOTION_ID, emotion_id);
        intent.putExtra(EXERCISE_MESSAGE_ID, exer_uid.toString());
        intent.putExtra(EXERCISE, getString(R.string.opposite_action));
        intent.putExtra(EXERSISE_TIMESTAMP, ts.toString());
        //Log.d("checking session id: ", exer_uid);
        startActivity(intent);
        finish();
    }

    public void startProblemSolving(View view) {
        UUID exer_uid = UUID.randomUUID();
        Long ts = System.currentTimeMillis();
        Intent intent = new Intent(this, TippActivity.class);
        intent.putExtra(SESSION_ID, session_id);
        intent.putExtra(TIMESTAMP_ID, session_ts);
        intent.putExtra(EMOTION_ID, emotion_id);
        intent.putExtra(EXERCISE_MESSAGE_ID, exer_uid.toString());
        intent.putExtra(EXERCISE, getString(R.string.tipp));
        intent.putExtra(EXERSISE_TIMESTAMP, ts.toString());
        startActivity(intent);
        finish();
    }

    public void startAudioExercise(View view) {
        UUID exer_uid = UUID.randomUUID();
        Long ts = System.currentTimeMillis();
        Intent intent = new Intent(this, AudioActivity.class);
        intent.putExtra(SESSION_ID, session_id);
        intent.putExtra(TIMESTAMP_ID, session_ts);
        intent.putExtra(EMOTION_ID, emotion_id);
        intent.putExtra(EXERCISE_MESSAGE_ID, exer_uid.toString());
        intent.putExtra(EXERCISE, getString(R.string.audio));
        intent.putExtra(EXERSISE_TIMESTAMP, ts.toString());
        startActivity(intent);
        finish();

    }
}
