package com.mpr.classinfinity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mpr.Adapter.CoursesItemAdapter;
import com.mpr.Model.Courses;
import com.mpr.classinfinity.databinding.ActivityCategoryCoursesBinding;

import java.net.URL;
import java.util.ArrayList;

public class CategoryCoursesActivity extends AppCompatActivity {

    ActivityCategoryCoursesBinding binding;
    int page = 1;
    private static String Sample_json_Response = "";
    ProgressBar spinner;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Sample_json_Response = getIntent().getStringExtra("Category");
        String category = getIntent().getStringExtra("CategoryType");

        UtilsAsyncTask task = new UtilsAsyncTask();
        task.execute();

        spinner = (ProgressBar) findViewById(R.id.progress_spineer);

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                String httpLink = "https://www.udemy.com/api-2.0/courses/?page=1&page_size=20&search=";
                httpLink += binding.searchEdittext.getText().toString().trim();
                Sample_json_Response = httpLink;

                UtilsAsyncTask task1 = new UtilsAsyncTask();
                task1.execute();
            }
        });

       /* binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    // binding.progressSpineer.setVisibility(View.VISIBLE);

                    // https://www.udemy.com/api-2.0/courses/?page=1&search=

                    *//*limit += 20;
                    Log.i(LOG_TAG,"LIMIT: "+limit);
                    Json_Link = tempLink;
                    Json_Link += limit;

                    binding.progressBarMoreResp.setVisibility(View.VISIBLE);
                    UtilsAsyncTaskMore task1 = new UtilsAsyncTaskMore();
                    task1.execute();

                    Log.i(LOG_TAG,"More Result Link: "+Json_Link);*//*



                }
            }
        });*/


    }

    protected void updateUi(ArrayList<Courses> booksInfos) {

        CoursesItemAdapter sliderAdapter = new CoursesItemAdapter(booksInfos, binding.recyclerView, getApplicationContext(), R.layout.courses_item_specific, 2);
        binding.recyclerView.setAdapter(sliderAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private class UtilsAsyncTask extends AsyncTask<URL, Void, ArrayList<Courses>> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected ArrayList<Courses> doInBackground(URL... urls) {
            ArrayList<Courses> event = QueryUtils.fetchCoursesData(Sample_json_Response);            //also we can use  urls[0]
            Log.i("CategoryCoursesActivity",Sample_json_Response);
            return event;
        }

        @Override
        protected void onPostExecute(ArrayList<Courses> event) {

            spinner.setVisibility(View.GONE);

            if (event == null) {
                binding.emptyNoBook.setText("No Books Found");
                return;
            }

            updateUi(event);

        }


    }
}