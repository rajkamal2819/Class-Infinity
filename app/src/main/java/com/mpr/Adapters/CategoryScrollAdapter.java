package com.mpr.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mpr.Models.Category;
import com.mpr.classinfinity.R;

import java.util.ArrayList;

public class CategoryScrollAdapter extends RecyclerView.Adapter<CategoryScrollAdapter.categoryViewHolder> {

    private ArrayList<Category> list;
    private RecyclerView recyclerView;
    private Context context;

    public CategoryScrollAdapter(ArrayList<Category> list, RecyclerView recyclerView, Context context) {
        this.list = list;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryScrollAdapter.categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryScrollAdapter.categoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryScrollAdapter.categoryViewHolder holder, int position) {
        holder.setImageView(list.get(position));
        Log.i("Slider item: ",""+list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class categoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categoryImage);
            textView = itemView.findViewById(R.id.categoryName);
        }

        void setImageView(Category sliderItem) {

            imageView.setImageResource(sliderItem.getCategoryImage());
            textView.setText(sliderItem.getCategoryName());


        }


    }


}