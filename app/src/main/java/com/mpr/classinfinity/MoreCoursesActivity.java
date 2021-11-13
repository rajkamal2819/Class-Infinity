package com.mpr.classinfinity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mpr.Adapters.Fragment3Adapter;
import com.mpr.Adapters.FragmentAdapter;
import com.mpr.classinfinity.databinding.ActivityMoreCoursesBinding;

public class MoreCoursesActivity extends AppCompatActivity {

    ActivityMoreCoursesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPagerMoreCourses.setAdapter(new Fragment3Adapter(getSupportFragmentManager()));
        binding.tabLayoutMoreCourses.setupWithViewPager(binding.viewPagerMoreCourses);
        binding.tabLayoutMoreCourses.setTabGravity(TabLayout.GRAVITY_FILL);

    }



}