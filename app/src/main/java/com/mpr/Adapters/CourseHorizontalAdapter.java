package com.mpr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.mpr.Models.Courses;
import com.mpr.classinfinity.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CourseHorizontalAdapter extends RecyclerView.Adapter<CourseHorizontalAdapter.CourseHorizontalHolder> {

    private ArrayList<Courses> list;
    private ViewPager2 viewPager2;
    private Context context;

    public CourseHorizontalAdapter(ArrayList<Courses> list, ViewPager2 viewPager2, Context context) {
        this.list = list;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseHorizontalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseHorizontalHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_horizontal_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHorizontalHolder holder, int position) {
        holder.setImageView(list.get(position));
        Log.i("Slider item: ",""+list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CourseHorizontalHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public CourseHorizontalHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.courseImage);
            textView = itemView.findViewById(R.id.courseName);
        }

        void setImageView(Courses sliderItem) {

            textView.setText(sliderItem.getTittle());
            if (sliderItem.getCourseThumbnail() != null) {
                try {
                    Glide.with(imageView.getContext())
                            .load(new URL(sliderItem.getCourseThumbnail()))
                            .into(imageView);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                Glide.with(imageView.getContext()).load(R.drawable.courses_icon).into(imageView);
            }
        }


    }


}