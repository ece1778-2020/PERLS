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
    private static final String POSITION = "position";
    private static final String ANSWERS = "answers";

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

        Intent intent = getIntent();
        session_id = intent.getStringExtra(SESSION_ID);
        session_ts = intent.getStringExtra(TIMESTAMP_ID);
        emotion_id = intent.getStringExtra(EMOTION_ID);
        exercise_uid = intent.getStringExtra(EXERCISE_MESSAGE_ID);
        exercise_ts = intent.getStringExtra(EXERSISE_TIMESTAMP);
        exercise = intent.getStringExtra(EXERCISE);
        //Log.d("checking session id: ", exercise_uid);
    }

    public void loadLesson() {
        if(exercise == "opposite action".trim()){
            mLessonList.add(getString(R.string.welcome_oa));
            mAdapter.notifyItemChanged(mLessonList.size() - 1);
        } else {
            mLessonList.add(getString(R.string.welcome_ps));
            mAdapter.notifyItemChanged(mLessonList.size() - 1);
        }


    }

    public void startExercise(View view) {
        ArrayList<String> answers = new ArrayList<>();
        Intent intent = new Intent(this, ExerciseActivity.class);
        Bundle data = new Bundle();
        data.putString(SESSION_ID, session_id);
        data.putString(TIMESTAMP_ID, session_ts);
        data.putString(EMOTION_ID, emotion_id);
        data.putString(EXERSISE_TIMESTAMP, exercise_ts);
        data.putString(EXERCISE_MESSAGE_ID, exercise_uid);
        data.putString(EXERCISE, exercise);
        data.putInt(POSITION, 1);
        data.putStringArrayList(ANSWERS, answers);
        intent.putExtras(data);
        finish();
        startActivity(intent);

    }
}
