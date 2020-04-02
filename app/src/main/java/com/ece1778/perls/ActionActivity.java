package com.ece1778.perls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ActionActivity extends AppCompatActivity {

    private ExerciseViewModel mViewModel;
    private TextView mTimer;
    private TextView mAction, mCongrats, mReflection;
    private Button mStartTimer, mReview, mCancel;
    private View mView;
    private static final String EXERCISE_COLLECTION = "exercises";
    private static final String SESSION_COLLECTION="sessions";
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";
    private static final String POSITION = "position";
    private static final String ANSWERS = "answers";
    private String session_ts, session_id, exercise_uid, exercise_ts, emotion_id, exercise_name;
    private int position;
    //private String[] answers;
    private ArrayList<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        mAction = findViewById(R.id.action_textView);
        mTimer = findViewById(R.id.timer_textView);
        mCongrats = findViewById(R.id.congs_textView);
        mStartTimer = findViewById(R.id.start_button);
        mReflection = findViewById(R.id.reflection_textView);
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
                        mCongrats.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        });

        mReflection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("View model uid: ", mViewModel.getUid());
                uploadExercise();
            }
        });

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        //Log.d("checking session id: ", intent.getStringExtra(EXERCISE));
        session_id = data.getString(SESSION_ID);
        session_ts = data.getString(TIMESTAMP_ID);
        emotion_id = data.getString(EMOTION_ID);
        exercise_ts = data.getString(EXERSISE_TIMESTAMP);
        exercise_uid  = data.getString(EXERCISE_MESSAGE_ID);
        exercise_name = data.getString(EXERCISE);
        //position = Integer.parseInt(intent.getStringExtra(POSITION));
        position = data.getInt(POSITION);
        //answers = data.getStringArray(ANSWERS);
        answers = data.getStringArrayList(ANSWERS);

        mAction.setText(answers.get(answers.size() - 1));

        Log.d("Test exercise: ", answers.toString());
        Log.d("Test lenght: ", ""+ answers.size());

        /*mViewModel.getAction().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                s = "You chose to " + s;
                mAction.setText(s);
            }
        });*/

    }

    public void uploadExercise(){
        //Log.d("View model uid: ", mViewModel.getUid());
        /*Question q1, q2, q3, q4, q5;
        String action;

        q1 = mViewModel.getQ1().getValue();
        q2 = mViewModel.getQ2().getValue();
        q3 = mViewModel.getQ3().getValue();
        q4 = mViewModel.getQ4().getValue();
        q5 = mViewModel.getQ5().getValue();
        action = mViewModel.getAction().getValue();*/
        Map<String, Object> exercise = new HashMap<>();
        exercise.put("uid", exercise_uid);
        exercise.put("timestamp", exercise_ts);
        exercise.put("name", exercise_name);
        exercise.put("emotion", emotion_id);
        exercise.put("q1", answers.get(0));
        exercise.put("q2", answers.get(1));
        exercise.put("q3", answers.get(2));
        exercise.put("q4", answers.get(3));
        exercise.put("q5", answers.get(4));
        exercise.put("action", answers.get(5));
        Log.d("Test exercise: ", answers.toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(EXERCISE_COLLECTION).document(exercise_uid)
                .set(exercise)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(ActionActivity.this, ReflectionActivity.class);
                        finish();
                        intent.putExtra(EMOTION_ID, emotion_id);
                        intent.putExtra(EXERCISE_MESSAGE_ID, exercise_uid);
                        intent.putExtra(TIMESTAMP_ID, session_ts);
                        intent.putExtra(SESSION_ID, session_id);
                        //requireActivity().finish();

                        startActivity(intent);
                        Log.d(TAG, "DocumentSnapshot exercise successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing exercise document, try again", e);
                        Toast.makeText(ActionActivity.this, "Failed upload, try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void cancelExercise(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}
