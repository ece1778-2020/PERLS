package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";

    private RecyclerView mRecyclerView;
    private LessonListAdapter mAdapter;
    private ArrayList<String> mLessonList;
    private ExerciseViewModel mViewModel;

    private String session_ts;
    private String session_id;
    private String exercise_uid;
    private String exercise_ts, exercise;
    private String emotion_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        //mViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        Intent intent = getIntent();
        /*mViewModel.setEmotion(intent.getStringExtra(EMOTION_ID));
        mViewModel.setUid(intent.getStringExtra(EXERCISE_MESSAGE_ID));
        mViewModel.setSession_ts(intent.getStringExtra(TIMESTAMP_ID));
        mViewModel.setExercise_name(EXERCISE);
        mViewModel.setTimestamp(intent.getStringExtra(EXERSISE_TIMESTAMP));
        mViewModel.setSession_uid(intent.getStringExtra(SESSION_ID));*/
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        exercise_uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        exercise_ts = intent.getStringExtra(EXERSISE_TIMESTAMP);
        exercise = intent.getStringExtra(EXERCISE);


        //add all the data and notify dataset chane or something
        mLessonList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.lessonRecyclerView);
        mAdapter = new LessonListAdapter(this, mLessonList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        //mLessonList.add("yay");
        //mAdapter.notifyItemChanged(mLessonList.size() - 1);
        loadLesson();
    }

    public void loadLesson() {
        //mAdapter.notifyItemChanged(mLessonList.size() - 1);
        /*mLessonList.add(getString(R.string.oa_l1));
        mLessonList.add(getString(R.string.oa_l2));
        mLessonList.add(getString(R.string.oa_l3));
        mLessonList.add(getString(R.string.oa_l4));
        mLessonList.add(getString(R.string.oa_l5));
        mLessonList.add(getString(R.string.oa_l6));
        mLessonList.add(getString(R.string.oa_l7));
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
        Log.d("load lesson fcn", "got into the fcn");*/

        if(exercise == "opposite action".trim()){
            mLessonList.add(getString(R.string.welcome_oa));
            mAdapter.notifyItemChanged(mLessonList.size() - 1);
        } else {
            mLessonList.add(getString(R.string.welcome_ps));
            mAdapter.notifyItemChanged(mLessonList.size() - 1);
        }


    }

    public void skip_lesson(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra(SESSION_ID, session_id);
        intent.putExtra(TIMESTAMP_ID, session_ts);
        intent.putExtra(EMOTION_ID, emotion_id);
        intent.putExtra(EXERCISE_MESSAGE_ID, exercise_uid);
        intent.putExtra(EXERCISE, exercise);
        intent.putExtra(EXERSISE_TIMESTAMP, exercise_ts);
        finish();
        startActivity(intent);
    }
}
