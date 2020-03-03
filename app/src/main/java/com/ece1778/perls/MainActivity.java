package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";

    private Button mExerciseBtn, mReflectionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();

        mExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID uid = UUID.randomUUID();
                String exerciseId = uid.toString();
                Long ts = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "exercise id generated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EmotionSelector.class);
                intent.putExtra(EXERCISE_MESSAGE_ID, exerciseId);
                intent.putExtra(TIMESTAMP_ID, ts.toString());
                startActivity(intent);
            }
        });
    }

    private void setUI(){
        mExerciseBtn = findViewById(R.id.exercise);
        mReflectionBtn = findViewById(R.id.reflection);
    }
}
