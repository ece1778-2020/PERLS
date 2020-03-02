package com.ece1778.perls;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0:
                return QuestionFragment.newInstance(0);
            case 1:
                return QuestionFragment.newInstance(1);

            case 2:
                return QuestionFragment.newInstance(2);

            case 3:
                return QuestionFragment.newInstance(3);

            case 4:
                return QuestionFragment.newInstance(4);

            case 5:
                return QuestionFragment.newInstance(5);

            case 6:
                return ActionFragment.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }



}
