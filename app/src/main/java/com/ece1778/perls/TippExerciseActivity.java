package com.ece1778.perls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TippExerciseActivity extends AppCompatActivity {

    private TextView mTimer, mIntro, mEx1, mEx2, mEx3, mEx4, mEx5;
    private Button mStartTimer, mDone;
    private ImageView mPlay;
    private ImageView mPause;
    private CountDownTimer mCounter;

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
        mEx1 = findViewById(R.id.ex1_textView);
        mEx2 = findViewById(R.id.ex2_textView);
        mEx3 = findViewById(R.id.ex3_textView);
        mEx4 = findViewById(R.id.ex4_textView);
        //mEx5 = findViewById(R.id.ex5);
        mDone = findViewById(R.id.done);
        mPlay = findViewById(R.id.button_play);
        mPause = findViewById(R.id.button_pause);

        mCounter = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                String t = (seconds / 60) + ":" + (seconds % 60);
                mTimer.setText(t);
            }

            public void onFinish() {

            }
        };

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlay.setVisibility(View.GONE);
                mPause.setVisibility(View.VISIBLE);
                mCounter.start();
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlay.setVisibility(View.VISIBLE);
                mPause.setVisibility(View.GONE);
                mCounter.cancel();

            }
        });

        Intent intent = getIntent();
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
            Picasso.get()
                    .load(R.drawable.tipp1_temp_cold_water_on_face)
                    .resize(500, 500)
                    .into((ImageView) findViewById(R.id.ex1_imageView));
            //mEx1.setText("-- Hold your breath and run your face over cold water.");
            Picasso.get()
                    .load(R.drawable.tipp1_ice_bag)
                    .resize(500, 500)
                    .into((ImageView) findViewById(R.id.ex2_imageView));
            //mEx2.setText("-- Hold an ice pack on your face, neck, or hands.");
        }
        else if (type.equals(getString(R.string.ie))) {
            mIntro.setText("Okay, initiate intense brief exercise to calm down your body when it is revved up by emotion.");
            Picasso.get()
                    .load(R.drawable.tipp2_physical_jumping_jacks)
                    .resize(450, 350)
                    .into((ImageView) findViewById(R.id.ex1_imageView));
            //mEx1.setText("-- Engage in an intense exercise such as jumping jacks on the spot");
            Picasso.get()
                    .load(R.drawable.tipp2_run_on_spot)
                    .resize(450, 350)
                    .into((ImageView) findViewById(R.id.ex2_imageView));
            //mEx2.setText("-- Expend your body's stored up physical energy by running on the spot");
            Picasso.get()
                    .load(R.drawable.tipp2_pushup)
                    .resize(450, 350)
                    .into((ImageView) findViewById(R.id.ex3_imageView));

            Picasso.get()
                    .load(R.drawable.tipp2_lift_weights)
                    .resize(450, 350)
                    .into((ImageView) findViewById(R.id.ex4_imageView));
        }
        else if (type.equals(getString(R.string.pb))) {
            mIntro.setText("Okay, initiate paced breathing to slow it down.");
            Picasso.get()
                    .load(R.drawable.tipp3_breathe_deep)
                    .resize(500, 500)
                    .into((ImageView) findViewById(R.id.ex1_imageView));
            //mEx1.setText("-- Breathe deeply into your belly.");
            Picasso.get()
                    .load(R.drawable.tipp3_deep_breathing)
                    .resize(500, 500)
                    .into((ImageView) findViewById(R.id.ex2_imageView));
            //mEx2.setText("-- Slow your pace of inhaling and exhaling (try to get down to 5-6 breaths per minute).");
        } else {
            mIntro.setText("Okay, initiate paired muscle relaxation to calm down while slowing your breathing too.");
            Picasso.get()
                    .load(R.drawable.tipp4_paired_relax_breathing)
                    .resize(500, 500)
                    .into((ImageView) findViewById(R.id.ex1_imageView));
            //mEx1.setText("-- While breathing into your belly deeply tense your body muscles.");
            Picasso.get()
                    .load(R.drawable.tipp4_paired_relax_say_out_loud)
                    .resize(500, 500)
                    .into((ImageView) findViewById(R.id.ex2_imageView));
            //mEx2.setText("-- While breathing out, say the word \"relax\" in your mind.");
        }


    }
}
