package com.mpr.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mpr.Fragments.LogInFragment;
import com.mpr.Fragments.MostReviewdCourses;
import com.mpr.Fragments.NewestCourses;
import com.mpr.Fragments.RelavanceCourses;
import com.mpr.Fragments.SignUpFragment;

public class Fragment3Adapter extends FragmentPagerAdapter {
    public Fragment3Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public Fragment3Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return new RelavanceCourses();
            case 1: return new MostReviewdCourses();
            case 2: return new NewestCourses();
            default: return new RelavanceCourses();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title = null;
        if(position==0){
            title = "Relavance";
        }
        else if(position==1){
            title = "High Rated";
        }
        else if(position == 2){
            title = "Newest";
        }

        return title;
    }

}

