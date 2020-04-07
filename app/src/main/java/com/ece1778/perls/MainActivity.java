package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String SESSION_ID="sessionId";

    private Button mExerciseBtn, mReflectionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        startAccelerometer();
        mExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID session_uid = UUID.randomUUID();
                Long ts = System.currentTimeMillis();
                Intent intent = new Intent(MainActivity.this, EmotionSelector.class);
                intent.putExtra(TIMESTAMP_ID, ts.toString());
                intent.putExtra(SESSION_ID, session_uid.toString());
                startActivity(intent);
            }
        });

        mReflectionBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, ReflectionReview.class));
                    }
        });
    }

    private void startAccelerometer() {
        Intent intent = new Intent(MainActivity.this, AgitationDetectorService.class);
        startService(intent);
    }

    private void setUI(){
        mExerciseBtn = findViewById(R.id.exercise);
        mReflectionBtn = findViewById(R.id.reflection);
    }
}
