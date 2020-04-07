package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    private ExerciseViewModel mViewModel;
    private OptionListAdapter mAdapter;
    private ViewPager mViewPager;
    private ImageView mNext, mPrevious;
    private String timestamp, uid, emotion;
    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private ArrayList<String> mOptionList;
    private static final String EXERCISE_MESSAGE_ID = "exerciseId";
    private static final String TIMESTAMP_ID = "timestamp";
    private static final String EMOTION_ID = "emotion";
    private static final String SESSION_ID="sessionId";
    private static final String EXERSISE_TIMESTAMP= "exersiseTimestamp";
    private static final String EXERCISE = "exercise_name";
    private static final String POSITION = "position";
    private static final String ANSWERS = "answers";
    private String session_ts, session_id, exercise_uid, exercise_ts, emotion_id, exercise;
    private int position;
    private ArrayList<String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        answers = new ArrayList<>();

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        //Log.d("checking session id: ", intent.getStringExtra(EXERCISE));
        session_id = data.getString(SESSION_ID);
        session_ts = data.getString(TIMESTAMP_ID);
        emotion_id = data.getString(EMOTION_ID);
        exercise_ts = data.getString(EXERSISE_TIMESTAMP);
        exercise_uid  = data.getString(EXERCISE_MESSAGE_ID);
        exercise = data.getString(EXERCISE);
        position = data.getInt(POSITION);
        answers = data.getStringArrayList(ANSWERS);
        mViewModel.setAnswers(answers);

        mTextView = findViewById(R.id.question);
        mOptionList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.optionRecyclerView);
        mAdapter = new OptionListAdapter(this, mOptionList, mViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData(position);

    }

    public void cancelExercise(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);

    }

    public void goNext(View view) {
        Intent intent;
        if (position == 6) {
            intent = new Intent(this, ActionActivity.class);
        } else {
            intent = new Intent(this, ExerciseActivity.class);
        }

        Bundle data = new Bundle();
        data.putString(SESSION_ID, session_id);
        data.putString(TIMESTAMP_ID, session_ts);
        data.putString(EMOTION_ID, emotion_id);
        data.putString(EXERSISE_TIMESTAMP, exercise_ts);
        data.putString(EXERCISE_MESSAGE_ID, exercise_uid);
        data.putString(EXERCISE, exercise);
        data.putInt(POSITION, position + 1);
        data.putStringArrayList(ANSWERS, answers);
        intent.putExtras(data);
        finish();
        startActivity(intent);

    }

    private void loadData(int position) {

        switch (position) {
            case 1:
                mTextView.setText("What are you reacting to?");
                mOptionList.add("Goal blocked.");
                mOptionList.add("Activity prevented/interrupted.");
                mOptionList.add("Someone hurt/attacked.");
                mOptionList.add("Someone insulted/threatened.");
                mOptionList.add("Integrity or morals offended. ");
                mOptionList.add("Physical or emotional pain.");
                mOptionList.add("None of these.");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
                break;

            case 2:
                mTextView.setText("Did you think any of the following?");
                mOptionList.add("Unfair treatment. ");
                mOptionList.add("This shouldn't have happened.");
                mOptionList.add("Others disagree.");
                mOptionList.add("This is wrong/unacceptable.");
                mOptionList.add("Ruminating about situation.");
                mOptionList.add("Feelings hurt.");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
                break;

            case 3:
                mTextView.setText("Did you feel any of the following?");
                mOptionList.add("Muscles tightening.");
                mOptionList.add("Teeth clamping.");
                mOptionList.add("Hands clenching.");
                mOptionList.add("Feeling flush/hot.");
                mOptionList.add("Being tearful/overwhelmed.");
                mOptionList.add("Wanting to attack/lash out.");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
                break;

            case 4:
                mTextView.setText("Do you feel your emotion is a valid reaction to the situation?");
                mOptionList.add("Yes");
                mOptionList.add("No");
                mOptionList.add("Maybe");
                mOptionList.add("Unsure");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
                break;

            case 5:
                if (exercise.equals(getString(R.string.opposite_action))) {
                    mTextView.setText("What is your urge to act on this emotion?");
                    mOptionList.add("Physically/verbally attacking");
                    mOptionList.add("Making aggressive/threatening gestures");
                    mOptionList.add("Pounding, throwing, breaking");
                    mOptionList.add("Walking heavily, stomping, slamming");
                    mOptionList.add("Walking out");
                    mOptionList.add("Using a loud, quarrelsome, sarcastic voice");
                    mOptionList.add("Clenching your hands and fists");
                    mOptionList.add("Frowning or showing a mean expression");
                    mOptionList.add("Brooding or withdrawing from others");
                    mOptionList.add("Crying");
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollToPosition(0);
                } else {
                    mTextView.setText("What has to happen for you to think you have made some progress?");
                    mOptionList.add("Relaxed hands");
                    mOptionList.add("Steady breath");
                    mOptionList.add("Stop crying");
                    mOptionList.add("Restrain yourself");
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollToPosition(0);
                }
                break;

            case 6:
                if (exercise.equals(getString(R.string.opposite_action))) {
                    mTextView.setText("If you want to feel differently, choose one of the opposite action:");
                } else {
                    mTextView.setText("Here are some solutions, choose one to continue:");
                }

                mOptionList.add("See the situation from their point of view");
                mOptionList.add("Relax muscles/unclench");
                mOptionList.add("Complete a half-smile with mouth.");
                mOptionList.add("Hold palms face up on lap.");
                mOptionList.add("Lower pace of breathing slowly.");
                mOptionList.add("Gently avoid the person/situation.");
                mOptionList.add("Half-agree or compliment angry person.");
                mOptionList.add("Physical but non-violent activity");
                mAdapter.notifyDataSetChanged();
                mRecyclerView.scrollToPosition(0);
                break;

            default:
                //return null;
        }
    }

}
