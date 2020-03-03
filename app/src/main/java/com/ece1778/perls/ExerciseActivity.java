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
    private ImageView mNext;
    private ImageView mPrevious;

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


    }

    public void cancelExercise(View view) {
        Intent intent = new Intent(this, MainActivity.class);
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
