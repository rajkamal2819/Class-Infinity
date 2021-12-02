package com.mpr.Fragmentss;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpr.classinfinity.R;
import com.mpr.classinfinity.databinding.FragmentNewestCoursesBinding;


public class NewestCourses extends Fragment {

    public NewestCourses() {
        // Required empty public constructor
    }

    FragmentNewestCoursesBinding binding;
    private String JSON_QUERY_RELEVANCE = "https://www.udemy.com/api-2.0/courses/?page=1&page_size=20&ordering=relevance";
    int page = 1, pageSize = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
       binding = FragmentNewestCoursesBinding.inflate(getLayoutInflater());



       return binding.getRoot();
    }



}