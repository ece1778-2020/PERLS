package com.ece1778.perls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ReflectionActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText mReflectionText;
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String REFLECTION_COLLECTION = "reflections";

    private String timestamp, uid, emotion, reflection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);

        mTextView = findViewById(R.id.textView);
        mReflectionText = findViewById(R.id.reflection_editText);
        //get this from the emotion passed from emotion selector -> exercise activity
        Intent intent = getIntent();
        emotion = intent.getStringExtra(EMOTION_ID);
        uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        timestamp = intent.getStringExtra(TIMESTAMP_ID);

        mTextView.setText("You were feeling " + emotion + " before the exercise, how do you feel now?");

    }

    public void goHome(View view) {
        String reflectionText = mReflectionText.getText().toString();
        if(reflectionText.length() < 1){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        Map<String, Object> reflection = new HashMap<>();
        reflection.put("uid", uid);
        reflection.put("prompt", mTextView.getText().toString());
        reflection.put("response", reflectionText);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(REFLECTION_COLLECTION).document(uid)
                .set(reflection)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot reflection successfully written!");
                        finish();
                        startActivity(new Intent(ReflectionActivity.this, MainActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing exercise document, try again", e);
                        Toast.makeText(ReflectionActivity.this, "Failed upload, try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
