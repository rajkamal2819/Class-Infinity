package com.mpr.classinfinity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.mpr.Models.Courses;

import java.util.ArrayList;

public class CoursesLoader extends AsyncTaskLoader<ArrayList<Courses>> {

    private static final String LOG_TAG = CoursesLoader.class.getName();

    private final String mQuery;

    CoursesLoader(Context context, String query) {
        super(context);
        this.mQuery = query;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG, "onStartLoading is called...");

        forceLoad();
    }

    @Override
    public ArrayList<Courses> loadInBackground() {

        Log.d(LOG_TAG, "loadInBackground is called...");

        if(TextUtils.isEmpty(mQuery)) {
            return null;
        }

        //noinspection UnnecessaryLocalVariable
        ArrayList<Courses> books = QueryUtils.fetchCoursesData(mQuery);
        return books;
    }
}