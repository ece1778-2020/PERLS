package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    private ExerciseViewModel mViewModel;
    private ViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private ImageView mNext, mPrevious;
    private String timestamp, uid, emotion;
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";
    private String session_ts, session_id, exercise_uid, exercise_ts, emotion_id, exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mAdapter = new ViewPagerAdapter(this, getSupportFragmentManager());
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        mNext = findViewById(R.id.next);
        mPrevious = findViewById(R.id.previous);
        //mPrevious.setVisibility(View.GONE);

        //uncomment this when activity is connected to EmotionSelector
        Intent intent = getIntent();
        //Log.d("checking session id: ", intent.getStringExtra(EXERCISE));
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        exercise_ts = intent.getStringExtra(EXERSISE_TIMESTAMP);
        exercise_uid  = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        exercise = intent.getStringExtra(EXERCISE);
        mViewModel.setEmotion(emotion_id);
        mViewModel.setUid(exercise_uid);
        mViewModel.setSession_ts(session_ts);
        mViewModel.setExercise_name(exercise);
        mViewModel.setTimestamp(exercise_ts);
        mViewModel.setSession_uid(session_id);
        mViewModel.setContext(this);


    }

    public void cancelExercise(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);

    }

    public void goNext(View view) {
        //Log.d("Exercise Activity", "getting into the goNext fcn");

        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    }

    public void goPrevious(View view) {
        //Log.d("Exercise Activity", "getting into the goPrevious fcn");
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
    }
}
