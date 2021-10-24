package com.mpr.classinfinity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mpr.Adapters.CourseAdapterList;
import com.mpr.Adapters.CourseHorizontalAdapter;
import com.mpr.Models.Courses;
import com.mpr.classinfinity.databinding.FragmentHomeBinding;

import java.net.URL;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    FragmentHomeBinding binding;
    private static final int BOOK_LOADER_ID = 1;
    CourseAdapterList mAdapter;
    private String JSON_QUERY = "https://www.udemy.com/api-2.0/courses/?page_size=12";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        HomeAsyncTask task = new HomeAsyncTask();
        task.execute();

        return binding.getRoot();
    }

    protected void updateUi(ArrayList<Courses> booksInfos) {

        CourseHorizontalAdapter adapter = new CourseHorizontalAdapter(booksInfos,binding.viewPager2,getContext());
        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.setClipToPadding(false);
        binding.viewPager2.setClipChildren(false);
        binding.viewPager2.setOffscreenPageLimit(3);
        binding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        adapter.notifyDataSetChanged();

    }

    private class HomeAsyncTask extends AsyncTask<URL, Void, ArrayList<Courses>> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected ArrayList<Courses> doInBackground(URL... urls) {
            ArrayList<Courses> event = QueryUtils.fetchCoursesData(JSON_QUERY);   //also we can use  urls[0]
            Log.d("HOme Fragment Utils",JSON_QUERY);
            return event;
        }

        @Override
        protected void onPostExecute(ArrayList<Courses> event) {

           // mLoadingIndicator.setVisibility(View.GONE);

            if(event==null){
                return;
            }

           // mEmptyListTextView.setText("No Books Found");
            updateUi(event);

        }

    }

}