package com.ece1778.perls;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ActionFragment extends Fragment {

    private ExerciseViewModel mViewModel;
    private TextView mTimer;
    private TextView mAction, mCongrats, mReflection;
    private Button mStartTimer, mReview, mCancel;
    private View mView;
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String EXERCISE_COLLECTION = "exercises";
    private static final String SESSION_ID="sessionId";
    private static final String SESSION_COLLECTION="sessions";

    public ActionFragment() {
        // Required empty public constructor
    }

    public static ActionFragment newInstance() {
        return new ActionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(requireActivity()).get(ExerciseViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_action, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAction = mView.findViewById(R.id.action_textView);
        mTimer = mView.findViewById(R.id.timer_textView);
        mCongrats = mView.findViewById(R.id.congs_textView);
        mStartTimer = mView.findViewById(R.id.start_button);
        mReflection = mView.findViewById(R.id.reflection_textView);
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
                Log.d("View model uid: ", mViewModel.getUid());
                uploadExercise();
            }
        });

        mViewModel.getAction().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                s = "You chose to " + s;
                mAction.setText(s);
            }
        });


    }

    public void uploadExercise(){
        //Log.d("View model uid: ", mViewModel.getUid());
        Question q1, q2, q3, q4, q5;
        String action;

        q1 = mViewModel.getQ1().getValue();
        q2 = mViewModel.getQ2().getValue();
        q3 = mViewModel.getQ3().getValue();
        q4 = mViewModel.getQ4().getValue();
        q5 = mViewModel.getQ5().getValue();
        action = mViewModel.getAction().getValue();
        Map<String, Object> exercise = new HashMap<>();
        exercise.put("uid", mViewModel.getUid());
        exercise.put("timestamp", mViewModel.getTimestamp());
        exercise.put("name", mViewModel.getExercise_name());
        exercise.put("emotion", mViewModel.getEmotion());
        exercise.put("q1", q1.getAnswer());
        exercise.put("q2", q2.getAnswer());
        exercise.put("q3", q3.getAnswer());
        exercise.put("q4", q4.getAnswer());
        exercise.put("q5", q5.getAnswer());
        exercise.put("action", action);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(EXERCISE_COLLECTION).document(mViewModel.getUid())
                .set(exercise)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(requireActivity(), ReflectionActivity.class);
                        intent.putExtra(EMOTION_ID, mViewModel.getEmotion());
                        intent.putExtra(EXERCISE_MESSAGE_ID, mViewModel.getUid());
                        intent.putExtra(TIMESTAMP_ID, mViewModel.getSession_ts());
                        intent.putExtra(SESSION_ID, mViewModel.getSession_uid());
                        //requireActivity().finish();
                        startActivity(intent);
                        Log.d(TAG, "DocumentSnapshot exercise successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing exercise document, try again", e);
                        Toast.makeText(getContext(), "Failed upload, try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
