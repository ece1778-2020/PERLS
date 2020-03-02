package com.ece1778.perls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    private ExerciseViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);

        mViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
    }

    public void cancelExercise(View view) {
        /*Log.d("View Model Q1: ", mViewModel.getQ1().getValue().getAnswer());
        Log.d("View Model Q2: ", mViewModel.getQ2().getValue().getAnswer());
        Log.d("View Model Q3: ", mViewModel.getQ3().getValue().getAnswer());
        Log.d("View Model Q4: ", mViewModel.getQ4().getValue().getAnswer());
        Log.d("View Model Q5: ", mViewModel.getQ5().getValue().getAnswer());*/
        //Log.d("cancelling...", "cancelling exercise");
        //Log.d("Cancelling: ", mViewModel.getQ1().getValue().getAnswer());
        //Toast.makeText(this, mViewModel.getQ2().getValue().getAnswer(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
