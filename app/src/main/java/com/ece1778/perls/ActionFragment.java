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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ActionFragment extends Fragment {

    private ExerciseViewModel mViewModel;
    private TextView mTimer;
    private TextView mAction, mCongrats, mReflection;
    private Button mStartTimer, mReview, mCancel;
    private View mView;
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";

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
                new CountDownTimer(120000, 1000) {

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
                Intent intent = new Intent(requireActivity(), ReflectionActivity.class);
                //uncomment this when exercise activity is connected to emotion selector
                /*intent.putExtra(EMOTION_ID, mViewModel.getEmotion());
                intent.putExtra(EXERCISE_MESSAGE_ID, mViewModel.getUid());
                intent.putExtra(TIMESTAMP_ID, mViewModel.getTimestamp());*/
                startActivity(intent);
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


}
