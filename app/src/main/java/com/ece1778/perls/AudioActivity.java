package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class AudioActivity extends AppCompatActivity {
    private SeekBar mSeekBar;
    private Button mPlay;
    private Button mPause;
    private MediaPlayer mediaPlayer;
    private Handler mHandler;
    private Runnable mRunnable;

    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";
    private static final String POSITION = "position";
    private static final String ANSWERS = "answers";

    private String session_ts,  session_id, exercise_uid, exercise_ts, exercise, emotion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        mediaPlayer = MediaPlayer.create(this, R.raw.progressive_muscle_relaxation_exercise);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setMax(mediaPlayer.getDuration());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {

                Log.d("Handler", "Running Handler");
                mSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                mHandler.postDelayed(mRunnable , 1000);
            }
        };

        mPlay = findViewById(R.id.button_play);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mHandler.postDelayed(mRunnable , 1000);
            }
        });
        mPause = findViewById(R.id.button_pause);
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        //handler = new Handler();


        Intent intent = getIntent();
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        exercise_uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        exercise_ts = intent.getStringExtra(EXERSISE_TIMESTAMP);
        exercise = intent.getStringExtra(EXERCISE);

    }

    public void startReflection(View view) {
        mediaPlayer.stop();
        mHandler.removeCallbacks(mRunnable);
        mediaPlayer.release();
        Intent intent = new Intent(this, ReflectionActivity.class);
        intent.putExtra(EMOTION_ID, emotion_id);
        intent.putExtra(EXERCISE_MESSAGE_ID, exercise_uid);
        intent.putExtra(TIMESTAMP_ID, session_ts);
        intent.putExtra(SESSION_ID, session_id);

        finish();
        startActivity(intent);
    }
}
