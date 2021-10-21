package com.mpr.classinfinity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.mpr.Adapters.CourseAdapterList;
import com.mpr.Models.Courses;
import com.mpr.classinfinity.databinding.FragmentBooksBinding;

import java.net.URL;
import java.util.ArrayList;

public class BooksFragment<BookAdapter> extends Fragment  /*implements LoaderManager.LoaderCallbacks<ArrayList<Courses>>*/ {

    public BooksFragment() {
        // Required empty public constructor
    }

    FragmentBooksBinding binding;
    private static final int BOOK_LOADER_ID = 1;
    private TextView mEmptyListTextView;
    private CourseAdapterList mAdapter;
    // private SearchView mSearchView;
    private View mLoadingIndicator;
    private String mQuery;

    private static String LOG_TAG = BooksFragment.class.getSimpleName();
    private String JSON_QUERY = "https://www.udemy.com/api-2.0/courses/?page=2";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(getLayoutInflater());

       // mSearchView = (SearchView) findViewById(R.id.search_view);
       // mSearchView.setQueryHint(getString(R.string.query_hint));

        ListView bookListView = (ListView) binding.list;

        // Find empty list TextView
        mEmptyListTextView = (TextView) binding.emptyView;
        bookListView.setEmptyView(mEmptyListTextView);

        //noinspection Convert2Diamond
        final ArrayList<Courses> books = new ArrayList();
        mAdapter = new CourseAdapterList(getContext(), books);

        bookListView.setAdapter(mAdapter);

        mLoadingIndicator = binding.loadingSpinner;

        HomeAsyncTask task = new HomeAsyncTask();
        task.execute();

        // Check for network state
        /*if (internetIsConnected()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            Log.d(LOG_TAG, "initLoader is called ...");
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            // Hide the progressBar spinner after loading
            mLoadingIndicator.setVisibility(View.GONE);

            // Set empty state text to display
            mEmptyListTextView.setText("No Internet Connection");
        }*/

        //Set an OnClickListener on every item of the ListView
       /* bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Create a Uri with the link of the current Book Object
                Uri link = Uri.parse(books.get(position).getmBookUrl());

                //Create an Intent with the link
                Intent webIntent = new Intent(Intent.ACTION_VIEW, link);

                //If there is an App to handle the Intent, start it
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });*/

        return binding.getRoot();
    }

    protected void updateUi(ArrayList<Courses> booksInfos) {
        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) binding.list;

        // Create a new {@link ArrayAdapter} of earthquakes
        CourseAdapterList itemAdapter = new CourseAdapterList(getContext(),booksInfos);
        // booksList = booksInfos;

        bookListView.setAdapter(itemAdapter);
        bookListView.setEmptyView(mEmptyListTextView);

        itemAdapter.notifyDataSetChanged();

    }

    private class HomeAsyncTask extends AsyncTask<URL, Void, ArrayList<Courses>> {
        @Override
        protected ArrayList<Courses> doInBackground(URL... urls) {
            ArrayList<Courses> event = QueryUtils.fetchCoursesData(JSON_QUERY);   //also we can use  urls[0]
            Log.d("HOme Fragment Utils",JSON_QUERY);
            return event;
        }

        @Override
        protected void onPostExecute(ArrayList<Courses> event) {

            mLoadingIndicator.setVisibility(View.GONE);

            if(event==null){
                return;
            }

            mEmptyListTextView.setText("No Books Found");
            updateUi(event);

        }

    }


/*
    @NonNull
    @Override
    public Loader<ArrayList<Courses>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader is called...");

        return new CoursesLoader(getContext(),JSON_QUERY);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Courses>> loader, ArrayList<Courses> courses) {
        Log.d(LOG_TAG, "onLoadFinished is called...");

        // Hide the progressBar spinner after loading
        View loadingIndicator = binding.loadingSpinner;
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display
        mEmptyListTextView.setText("No Courses");

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (courses != null && !courses.isEmpty()) {
            mAdapter.addAll(courses);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Courses>> loader) {
        Log.d(LOG_TAG, "onLoaderReset is called...");

        mAdapter.clear();
    }

    private boolean internetIsConnected() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }*/

}