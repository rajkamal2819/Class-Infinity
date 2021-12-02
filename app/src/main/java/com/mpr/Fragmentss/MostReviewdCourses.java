package com.mpr.Fragmentss;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpr.classinfinity.R;
import com.mpr.classinfinity.databinding.FragmentMostReviewdCoursesBinding;


public class MostReviewdCourses extends Fragment {


    public MostReviewdCourses() {
        // Required empty public constructor
    }

    FragmentMostReviewdCoursesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMostReviewdCoursesBinding.inflate(getLayoutInflater());




        return  binding.getRoot();
    }


}