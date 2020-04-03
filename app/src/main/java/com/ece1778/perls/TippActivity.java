package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TippActivity extends AppCompatActivity {
    private CardView mCardTT, mCardIE, mCardPB, mCardPMR;

    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";
    private static final String TYPE = "type";

    private String session_ts,  session_id, exercise_uid, exercise_ts, exercise, emotion_id;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp);

        mCardTT = findViewById(R.id.card_tt);
        mCardIE = findViewById(R.id.card_ie);
        mCardPB = findViewById(R.id.card_pb);
        mCardPMR = findViewById(R.id.card_pmr);
        mIntent = new Intent(TippActivity.this, TippExerciseActivity.class);

        Intent intent = getIntent();
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        exercise_uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        exercise_ts = intent.getStringExtra(EXERSISE_TIMESTAMP);
        exercise = intent.getStringExtra(EXERCISE);

        setIntent();
        setListeners();

    }

    private void setListeners(){
        mCardTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(TippActivity.this, ReflectionActivity.class);
                mIntent.putExtra(TYPE, getString(R.string.tt));
                finish();
                startActivity(mIntent);

            }
        });

        mCardIE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE, getString(R.string.ie));
                finish();
                startActivity(mIntent);

            }
        });

        mCardPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE, getString(R.string.pb));
                finish();
                startActivity(mIntent);

            }
        });

        mCardPMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE, getString(R.string.pmr));
                finish();
                startActivity(mIntent);

            }
        });
    }

    private void setIntent() {
        mIntent.putExtra(EMOTION_ID, emotion_id);
        mIntent.putExtra(EXERCISE_MESSAGE_ID, exercise_uid);
        mIntent.putExtra(EXERSISE_TIMESTAMP, exercise_ts);
        mIntent.putExtra(TIMESTAMP_ID, session_ts);
        mIntent.putExtra(SESSION_ID, session_id);
        mIntent.putExtra(EXERCISE, exercise);

    }

}
