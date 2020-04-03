package com.ece1778.perls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TippExerciseActivity extends AppCompatActivity {

    private TextView mTimer, mIntro, mEx1, mEx2, mEx3, mEx4, mEx5;
    private Button mStartTimer, mDone;

    private static final String EXERCISE_COLLECTION = "exercises";
    private static final String SESSION_COLLECTION="sessions";
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";
    private static final String TYPE = "type";
    private String session_ts, session_id, exercise_uid, exercise_ts, emotion_id, exercise_name, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipp_exercise);

        mTimer = findViewById(R.id.timer_textView);
        mStartTimer = findViewById(R.id.start);
        mIntro = findViewById(R.id.tipp_intro);
        mEx1 = findViewById(R.id.ex1);
        mEx2 = findViewById(R.id.ex2);
        mEx3 = findViewById(R.id.ex3);
        mEx4 = findViewById(R.id.ex4);
        mEx5 = findViewById(R.id.ex5);
        mDone = findViewById(R.id.done);

        mStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        long seconds = millisUntilFinished / 1000;
                        String t = (seconds / 60) + ":" + (seconds % 60);
                        mTimer.setText(t);
                    }

                    public void onFinish() {

                    }
                }.start();
            }
        });

        Intent intent = getIntent();
        //Bundle data = intent.getExtras();
        //Log.d("checking session id: ", intent.getStringExtra(EXERCISE));
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        exercise_ts = intent.getStringExtra(EXERSISE_TIMESTAMP);
        exercise_uid  = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        exercise_name = intent.getStringExtra(EXERCISE);
        type = intent.getStringExtra(TYPE);

        loadData();

    }

    public void startReflection(View view) {
        Map<String, Object> exercise = new HashMap<>();
        exercise.put("uid", exercise_uid);
        exercise.put("timestamp", exercise_ts);
        exercise.put("name", exercise_name);
        exercise.put("emotion", emotion_id);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(EXERCISE_COLLECTION).document(exercise_uid)
                .set(exercise)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(TippExerciseActivity.this, ReflectionActivity.class);
                        intent.putExtra(EMOTION_ID, emotion_id);
                        intent.putExtra(EXERCISE_MESSAGE_ID, exercise_uid);
                        intent.putExtra(TIMESTAMP_ID, session_ts);
                        intent.putExtra(SESSION_ID, session_id);
                        finish();
                        startActivity(intent);
                        Log.d(TAG, "DocumentSnapshot exercise successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing exercise document, try again", e);
                        Toast.makeText(TippExerciseActivity.this, "Failed upload, try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void loadData() {
        if (type.equals(getString(R.string.tt))) {
            mIntro.setText("Okay, tip the temperature of your face using cold water to calm down fast.");
            mEx1.setText("- Hold your breath and run your face over cold water.");
            mEx2.setText("- Hold an ice pack (or zip-lock bag of cold water) on your face, neck, or hands.");
        }
        else if (type.equals(getString(R.string.ie))) {
            mIntro.setText("Okay, initiate intense brief exercise to calm down your body when it is revved up by emotion.");
            mEx1.setText("- Engage in an intense exercise such as jumping jacks on the spot for 30 seconds.");
            mEx2.setText("- Expend your body's stored up physical energy by running on the spot, walking around, jumping, lifting weights, pushups, etc.");
        }
        else if (type.equals(getString(R.string.pb))) {
            mIntro.setText("Okay, initiate paced breathing to slow it down.");
            mEx1.setText("- Breathe deeply into your belly.");
            mEx2.setText("- Slow your pace of inhaling and exhaling (try to get down to 5-6 breaths per minute).");
            mEx3.setText("- Breathe out more slowly than you breathe in (e.g., 5 seconds in and 7 seconds out).");
        } else {
            mIntro.setText("Okay, initiate paired muscle relaxation to calm down while slowing your breathing too.");
            mEx1.setText("- While breathing into your belly deeply tense your body muscles (not so much as to cause a cramp).");
            mEx2.setText("- Notice the tension in your body.");
            mEx3.setText("- While breathing out, say the word \"relax\" in your mind.");
            mEx4.setText("- Let go of all the tension. ");
            mEx5.setText("- Notice the difference in your body.");
        }


    }
}
